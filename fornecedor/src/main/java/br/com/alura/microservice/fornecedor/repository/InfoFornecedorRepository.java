package br.com.alura.microservice.fornecedor.repository;

import br.com.alura.microservice.fornecedor.entity.InfoFornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoFornecedorRepository extends CrudRepository<InfoFornecedor, Long> {

	InfoFornecedor findByEstado( String estado );
}
