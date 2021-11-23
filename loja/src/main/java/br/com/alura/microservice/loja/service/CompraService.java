package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.repository.CompraRepository;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

	private static final Logger logger = LoggerFactory.getLogger( CompraService.class );

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private CompraRepository compraRepository;

	public Compra findById( Long id ) {
		return compraRepository.findById( id ).orElseThrow( () -> new ObjectNotFoundException( Compra.class, id.toString() ) );
	}

	public Compra realizaCompra( CompraDTO compra ) {
		logger.info( "Realizando busca do fornecedor no estado " + compra.getEndereco().getEstado() );
		var infoFornecedor = fornecedorClient.getInfoPorEstado( compra.getEndereco().getEstado() );
		logger.info( "Realizando processo de compra" );
		var pedido = fornecedorClient.realizaPedido( compra.getItens() );

		var compraSalva = new Compra();
		compraSalva.setPedidoId( pedido.getId() );
		compraSalva.setTempoDePreparo( pedido.getTempoDePreparo() );
		compraSalva.setEnderecoDestino( compra.getEndereco().toString() );

		compraRepository.save( compraSalva );

		return compraSalva;
	}
}
