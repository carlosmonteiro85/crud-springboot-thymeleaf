package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String formLogin() {
		System.out.println("Abrindo tela de login");
		return "login";
	}
	
	@RequestMapping("formCadastro")
	public String formCadastro() {
		System.out.println("Abrindo Formulario de Cadastro");
		return "form-cadastro";
	}
	
}
