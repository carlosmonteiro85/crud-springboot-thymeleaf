package br.com.inicial.crud_sprinboot_thymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Categoria categoria ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}