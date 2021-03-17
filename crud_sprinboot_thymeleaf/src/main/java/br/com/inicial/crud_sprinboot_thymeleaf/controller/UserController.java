package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.RequisicaoUsuario;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.repository.UserRepository;

@Controller
@RequestMapping("usuario")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public Iterable<User> list() {
		return repository.findAll();
	}

	@PostMapping("/logando")
	public String autenticando(@Valid RequisicaoUsuario requisicao, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		}
		return "login";
	}
	
	@PostMapping("addUsuario")
	public String addUsuario(@Valid RequisicaoUsuario requisicao, BindingResult result, RedirectAttributes attributes) {
		
		User usuario = new User();
		
		if (result.hasErrors()) {
			return "form-cadastro";
		}
		usuario = requisicao.toUsuario();
		/*Categoria padrao para novo usuario, apenas o perfil de adm pode alterar e categoria*/
		usuario.setCategoria(Categoria.USUARIO);
		usuario.setEnabled(true);
		usuario.setUsername(usuario.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		usuario.setPassworda(encoder.encode(usuario.getPassword()));
		repository.save(usuario);
		
		attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso");
		return "redirect:/login";
	}

}