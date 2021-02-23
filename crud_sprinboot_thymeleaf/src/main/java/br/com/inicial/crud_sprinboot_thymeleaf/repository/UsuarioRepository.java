package br.com.inicial.crud_sprinboot_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	

}
