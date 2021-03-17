package br.com.inicial.crud_sprinboot_thymeleaf.dto;




import javax.validation.constraints.NotBlank;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;

public class RequisicaoUsuario {

	@NotBlank
	private String nome;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	
	private Categoria categoria;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
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
	
	public User toUsuario() {
		User usuario = new User();
		usuario.setNome(nome);
		usuario.setPassworda(password);
		usuario.setEmail(email);
		usuario.setCategoria(categoria);
		return usuario;
	}
}
