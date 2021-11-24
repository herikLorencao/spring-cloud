package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoEntregaDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.controller.dto.VoucherDTO;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.model.CompraState;
import br.com.alura.microservice.loja.repository.CompraRepository;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

	private static final Logger logger = LoggerFactory.getLogger( CompraService.class );

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private TransportadorClient transportadorClient;

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	private Compra compraSalva;

	public Compra findById( Long id ) {
		return circuitBreakerFactory.create( "findById", "findById" ).run( () -> {
			return compraRepository.findById( id ).orElseThrow( () -> new ObjectNotFoundException( Compra.class, id.toString() ) );
		} );
	}

	public Compra realizaCompra( CompraDTO compra ) {
		return circuitBreakerFactory.create( "realizaCompra", "realizaCompra" ).run( () -> realizaProcessoCompra( compra ), t -> {
			return realizaCompraFallback( compra );
		} );
	}

	private Compra realizaProcessoCompra( CompraDTO compra ) {
		logger.info( "Realizando busca do fornecedor no estado " + compra.getEndereco().getEstado() );
		compraSalva = new Compra();
		compraSalva.setState( CompraState.RECEBIDO );
		compraSalva.setEnderecoDestino( compra.getEndereco().toString() );
		compraRepository.save( compraSalva );
		compra.setCompraId( compraSalva.getId() );

		// Se lançar alguma exceção cai no fallback e manda a compra no estado processado até o momento
//		if (true)
//			throw new RuntimeException();

		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado( compra.getEndereco().getEstado() );
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido( compra.getItens() );
		compraSalva.setState( CompraState.PEDIDO_REALIZADO );
		compraSalva.setPedidoId( pedido.getId() );
		compraSalva.setTempoDePreparo( pedido.getTempoDePreparo() );
		compraRepository.save( compraSalva );

		InfoEntregaDTO entregaDto = new InfoEntregaDTO();
		entregaDto.setPedidoId( pedido.getId() );
		entregaDto.setDataParaEntrega( LocalDate.now().plusDays( pedido.getTempoDePreparo() ) );
		entregaDto.setEnderecoOrigem( info.getEndereco() );
		VoucherDTO voucher = transportadorClient.reservaEntrega( entregaDto );
		compraSalva.setState( CompraState.RESERVA_ENTREGA_REALIZADA );
		compraSalva.setDataParaEntrega( voucher.getPrevisaoParaEntrega() );
		compraSalva.setVoucher( voucher.getNumero() );
		compraRepository.save( compraSalva );

		return compraSalva;
	}

	public Compra realizaCompraFallback( CompraDTO compra ) {
		if (compraSalva != null && compraSalva.getId() != null)
			return compraSalva;

		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino( compra.getEndereco().getRua() );
		return compraFallback;
	}

}
