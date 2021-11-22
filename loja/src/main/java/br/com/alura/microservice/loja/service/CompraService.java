package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {
	public void realizaCompra( CompraDTO compra) {
		var client = new RestTemplate();
		var exchange = client.exchange( "http://fornecedor/info/" + compra.getEndereco().getEstado(), HttpMethod.GET, null, InfoFornecedorDTO.class );
		System.out.println(exchange.getBody().getEndereco());
	}
}
