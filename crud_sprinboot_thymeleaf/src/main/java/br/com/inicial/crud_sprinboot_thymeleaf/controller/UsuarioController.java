package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.RequisicaoUsuario;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
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

	@PostMapping("/logando")
	public String autenticando(@Valid RequisicaoUsuario requisicao, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		}
		return "login";
	}
	
	@PostMapping("addUsuario")
	public String addUsuario(@Valid RequisicaoUsuario requisicao, BindingResult result, RedirectAttributes attributes) {
		
		Usuario usuario = new Usuario();
		
		if (result.hasErrors()) {
			return "form-cadastro";
		}
		usuario = requisicao.toUsuario();
		/*Categoria padrao para novo usuario, apenas o perfil de adm pode alterar e categoria*/
		usuario.setCategoria(Categoria.USUARIO);
		repository.save(usuario);
		
		attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso");
		return "redirect:/login";
	}

}