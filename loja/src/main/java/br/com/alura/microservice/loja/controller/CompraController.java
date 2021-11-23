package br.com.alura.microservice.loja.controller;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/compra" )
public class CompraController {

	@Autowired
	private CompraService compraService;

	@GetMapping( "/{id}" )
	public Compra findById( @PathVariable Long id ) {
		return compraService.findById( id );
	}

	@PostMapping
	public Compra realizaCompra( @RequestBody CompraDTO compra ) {
		return compraService.realizaCompra( compra );
	}
}
