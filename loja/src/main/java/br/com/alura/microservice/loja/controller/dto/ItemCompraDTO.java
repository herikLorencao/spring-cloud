package br.com.alura.microservice.loja.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCompraDTO {
	private Long id;
	private Integer quantidade;
}
