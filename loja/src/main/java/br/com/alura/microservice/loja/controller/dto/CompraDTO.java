package br.com.alura.microservice.loja.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraDTO {

	@JsonIgnore
	private Long compraId;
	private List<ItemCompraDTO> itens;
	private EnderecoDTO endereco;
}
