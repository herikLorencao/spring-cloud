package br.com.alura.microservice.loja.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoPedidoDTO {
	private Long id;
	private Integer tempoDePreparo;
}
