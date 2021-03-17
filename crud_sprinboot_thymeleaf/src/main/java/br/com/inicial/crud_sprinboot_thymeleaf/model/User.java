package br.com.inicial.crud_sprinboot_thymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //entidade para mapeamento do jpa
@Table(name = "users")
public class User {
	
	@Id
	private String username;
	@Column(nullable = false) // atributo não pode ser null
	private String nome;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)	
	private String email;
	@Enumerated(EnumType.ORDINAL)
	private Categoria categoria ;
	
	private Boolean enabled;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassworda(String password) {
		this.password = password;
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}