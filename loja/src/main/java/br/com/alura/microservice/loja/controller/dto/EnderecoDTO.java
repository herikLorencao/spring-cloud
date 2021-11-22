package br.com.alura.microservice.loja.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	private String rua, estado;
	private Integer numero;
}
