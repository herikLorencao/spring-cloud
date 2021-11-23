package br.com.alura.microservice.loja.client;

import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.controller.dto.ItemCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient( "fornecedor" )
public interface FornecedorClient {
	@GetMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado( @PathVariable String estado );

	@PostMapping("/pedido")
	InfoPedidoDTO realizaPedido( List<ItemCompraDTO> itens );
}
