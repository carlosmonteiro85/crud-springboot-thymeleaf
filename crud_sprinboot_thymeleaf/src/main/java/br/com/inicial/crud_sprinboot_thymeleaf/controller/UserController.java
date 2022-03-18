package br.com.inicial.crud_sprinboot_thymeleaf.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserProfileDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.service.UserService;
import br.com.inicial.crud_sprinboot_thymeleaf.util.CriptografiaPassword;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public String list(Model model) {
		List<User> users = service.findAll();
		model.addAttribute("users", users);
		return "all-users";
	}

	@GetMapping("/{enabled}")
	public String listAllEnabled(Model model, @PathVariable("enabled") boolean enabled) {
		List<User> users = service.fingByEnabled(enabled);
		model.addAttribute("users", users);
		return "all-users";
	}

	@GetMapping("/all/{role}")
	public String listAllEnabled(Model model, @PathVariable("role") Categoria role) {
		List<User> users = service.fingByRole(role);
		model.addAttribute("users", users);
		return "all-users";
	}

	@GetMapping("profile/{id}")
	public String profile(@PathVariable("id") Long id, Model model) {
		Optional<User> user = service.findById(id);
		UserProfileDTO userEditDto = UserProfileDTO.UserToUserProfileDTO(user.get());
		model.addAttribute("user", userEditDto);
		return "profile";
	}

	@RequestMapping("delected")
	public String excluir(String id, RedirectAttributes attributes) {
		Optional<User> user = service.findById(Long.parseLong(id));

		if (!user.isPresent()) {
			attributes.addFlashAttribute("msnErro", "Não foi possível excluír este usuário!");
			return "redirect:/user";
		}
		service.deleteById(user.get().getId());
		attributes.addFlashAttribute("msnSucess", "Usuario excluído com sucesso!");
		return "redirect:/user";
	}

	@GetMapping("actived")
	public String activedUser(String id, RedirectAttributes attributes) {

		Optional<User> user = service.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			return "all-users";
		}
		if (user.get().getEnabled() != true) {
			user.get().setEnabled(true);
			service.update(user.get());
			attributes.addFlashAttribute("msnSucess", "Usuario ativado com sucesso.");
		} else {
			attributes.addFlashAttribute("msnErro", "Este usuário já esta ativado.");
		}
		return "redirect:/user";
	}

	@PostMapping("update")
	public String updateUser(@Valid UserProfileDTO requisicao, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors() || requisicao.getEmail().isEmpty()) {
			return "form-cadastro";
		}

		Optional<User> user = service.findById(requisicao.getId());

		if (requisicao.getPassword() != null) {
			String confirmPass = CriptografiaPassword.encryptMD5(requisicao.getConfirmPass());
			String password = CriptografiaPassword.encryptMD5(requisicao.getPassword());
			if (!password.equals(confirmPass) || requisicao.getPassword().isEmpty() || !user.isPresent()
					|| !user.get().getPassword().equals(password)) {
				attributes.addFlashAttribute("msnErro", "Erro ao atualizar o usuário " + requisicao.getName() + ".");
				return "redirect:/user";
			}
			user.get().setPassword(password);
		}

		user.get().setName(requisicao.getName());
		user.get().setEmail(requisicao.getEmail());
		user.get().setEnabled(requisicao.getEnabled());
		user.get().setRole(requisicao.getRole());

		service.update(user.get());

		attributes.addFlashAttribute("msnSucess", "O usuario " + requisicao.getName() + " foi atualizado com sucesso.");
		return "redirect:/user";
	}

	@RequestMapping("profileUser")
	public String profileUser(Model model, HttpServletRequest servletRequest) {
		HttpSession sessao = servletRequest.getSession();
		UserProfileDTO usuarioLogado = (UserProfileDTO) sessao.getAttribute("userSession");
		model.addAttribute("user", usuarioLogado);
		return "profileUser";
	}

}