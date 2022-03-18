package br.com.inicial.crud_sprinboot_thymeleaf.dto;

import javax.validation.constraints.NotBlank;

import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.util.CriptografiaPassword;
import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	
	public User userToUserDTO() {
		User user = new User();
		user.setName(name);
		user.setPassword(CriptografiaPassword.encryptMD5(password));
		user.setEmail(email);
		return user;
	}
}
