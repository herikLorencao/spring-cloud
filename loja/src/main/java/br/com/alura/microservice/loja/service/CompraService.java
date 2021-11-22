package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

	@Autowired
	private RestTemplate client;

	@Autowired
	private DiscoveryClient eurekaClient;

	public void realizaCompra( CompraDTO compra ) {
		var exchange = client.exchange( "http://fornecedor/info/" + compra.getEndereco().getEstado(), HttpMethod.GET, null, InfoFornecedorDTO.class );

		eurekaClient.getInstances( "fornecedor" ).stream()
				.forEach( endereco -> System.out.println( endereco.getHost() + ":" + endereco.getPort() ) );

		System.out.println( exchange.getBody().getEndereco() );
	}
}
