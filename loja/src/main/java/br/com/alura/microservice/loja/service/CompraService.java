package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra( CompraDTO compra ) {
		var infoFornecedor = fornecedorClient.getInfoPorEstado( compra.getEndereco().getEstado() );
		var pedido = fornecedorClient.realizaPedido(compra.getItens());

		var compraSalva = new Compra();
		compraSalva.setPedidoId( pedido.getId() );
		compraSalva.setTempoDePreparo( pedido.getTempoDePreparo() );
		compraSalva.setEnderecoDestino( compra.getEndereco().toString() );

		return compraSalva;
	}
}
