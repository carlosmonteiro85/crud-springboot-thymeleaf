package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.RequisicaoUsuario;

@Controller
public class HomeController {

	@RequestMapping("/login")
	public String formLogin(RequisicaoUsuario requisicao) {
		System.out.println("Abrindo tela de login");
		return "login";
	}
	
	@RequestMapping("formCadastro")
	public String formCadastro(RequisicaoUsuario requisicao) {
		System.out.println("Abrindo Formulario de Cadastro");
		return "form-cadastro";
	}
	
	@RequestMapping("/")
	public String login(RequisicaoUsuario requisicao) {
		return "redirect:/login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
}
