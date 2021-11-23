package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.repository.InfoFornecedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

	private static final Logger logger = LoggerFactory.getLogger( InfoService.class );

	@Autowired
	private InfoFornecedorRepository infoFornecedorRepository;

	public InfoFornecedor getInfoPorEstado( String estado ) {
		logger.info( "Realizando busca de fornecedor pelo estado " + estado );
		return infoFornecedorRepository.findByEstado( estado );
	}
}
