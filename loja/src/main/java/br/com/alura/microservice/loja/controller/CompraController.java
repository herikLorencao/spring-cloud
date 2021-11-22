package br.com.alura.microservice.loja.controller;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping( "/compra" )
public class CompraController {

	@PostMapping
	public void realizaCompra( @RequestBody CompraDTO compra ) {
		var client = new RestTemplate();
		var exchange = client.exchange( "http://localhost:8081/info/" + compra.getEndereco().getEstado(), HttpMethod.GET, null, InfoFornecedorDTO.class );
		System.out.println(exchange.getBody().getEndereco());

	}
}
