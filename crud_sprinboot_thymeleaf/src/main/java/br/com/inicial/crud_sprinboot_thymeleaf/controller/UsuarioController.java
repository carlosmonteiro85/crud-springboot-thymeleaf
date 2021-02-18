package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Usuario;
import br.com.inicial.crud_sprinboot_thymeleaf.repository.UsuarioRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
    public Iterable<Usuario> list() {
        return repository.findAll();
    }
	
}
