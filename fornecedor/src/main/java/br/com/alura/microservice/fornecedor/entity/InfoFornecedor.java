package br.com.alura.microservice.fornecedor.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InfoFornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String endereco;
	private String estado;

	@Override
	public boolean equals( Object o ) {
		if( this == o )
			return true;
		if( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) )
			return false;
		InfoFornecedor that = ( InfoFornecedor ) o;
		return id != null && Objects.equals( id, that.id );
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
