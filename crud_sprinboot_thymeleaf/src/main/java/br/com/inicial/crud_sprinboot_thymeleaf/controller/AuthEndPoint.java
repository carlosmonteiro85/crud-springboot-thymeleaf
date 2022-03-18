package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.LoginDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserProfileDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.service.AuthService;

@Controller
@RequestMapping("/")
public class AuthEndPoint {

	@Autowired
	private AuthService authService;

	@PostMapping("auth")
	public String authUser(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes attributes, Model model, ServletRequest servletRequest) {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession sessao = request.getSession();
		
		if (result.hasErrors()) {
			return "sing-in";
		}
		
		UserProfileDTO userSession = authService.login(loginDTO);
		
		if (userSession == null) {
			attributes.addFlashAttribute("mensagem", "Login incorreto");
			return "redirect:/";
		}
		sessao.setAttribute("userSession", userSession);
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		session.removeAttribute("userSession");
		return "redirect:/login";
	}
	

}
