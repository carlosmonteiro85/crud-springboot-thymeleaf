package br.com.inicial.crud_sprinboot_thymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data //anotação do lombok que gera os métodos gets e sets automático
@Entity //entidade para mapeamento do jpa
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Gera a chave privaria auto incrementada
	private Long id;
	@Column(nullable = false) // atributo não pode ser null
	private String nome;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false, unique = true)	
	private String email;
	@Enumerated(EnumType.ORDINAL)
	private Categoria categoria;
}