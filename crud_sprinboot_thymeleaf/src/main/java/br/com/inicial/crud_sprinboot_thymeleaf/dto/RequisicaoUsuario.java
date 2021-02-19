package br.com.inicial.crud_sprinboot_thymeleaf.dto;




import javax.validation.constraints.NotBlank;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Usuario;

public class RequisicaoUsuario {

	@NotBlank
	private String nome;
	@NotBlank
	private String senha;
	@NotBlank
	private String email;
	
	private Categoria categoria;
	
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
	
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.setCategoria(categoria);
		return usuario;
	}
}
