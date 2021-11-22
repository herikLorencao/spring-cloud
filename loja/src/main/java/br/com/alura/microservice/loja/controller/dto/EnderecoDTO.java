package br.com.alura.microservice.loja.controller.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
	private String rua, estado;
	private Integer numero;
}
