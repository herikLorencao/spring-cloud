package br.com.alura.microservice.loja.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	private String rua, estado;
	private Integer numero;

	@Override
	public String toString() {
		return "EnderecoDTO{" + "rua='" + rua + '\'' + ", estado='" + estado + '\'' + ", numero=" + numero + '}';
	}
}
