package br.com.alura.microservice.loja.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraDTO {
	private List<ItemCompraDTO> itens;
	private EnderecoDTO endereco;
}
