package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.LoginDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService service;

	@RequestMapping("formCadastro")
	public String formCadastro(UserDTO requisicao) {
		return "form-cadastro";
	}

	@RequestMapping(value = { "", "login" })
	public String loginview(Model model, LoginDTO loginDTO) {
		return "sing-in";
	}

	@GetMapping("home")
	public String home(Model model, LoginDTO loginDTO) {
		return "home";
	}

	@PostMapping("add")
	public String addUsuario(@Valid UserDTO requisicao, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return "form-cadastro";
		}

		User user = new User();
		user = requisicao.userToUserDTO();
		service.save(user);

		attributes.addFlashAttribute("mensagem",
				"Cadastro efetuado com sucesso, aguarde que seu cadastro será ativado pelo admin");
		return "redirect:/";
	}
}
