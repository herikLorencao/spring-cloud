package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.repository.InfoFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

	@Autowired
	private InfoFornecedorRepository infoFornecedorRepository;

	public InfoFornecedor getInfoPorEstado( String estado ) {
		return infoFornecedorRepository.findByEstado( estado );
	}
}
