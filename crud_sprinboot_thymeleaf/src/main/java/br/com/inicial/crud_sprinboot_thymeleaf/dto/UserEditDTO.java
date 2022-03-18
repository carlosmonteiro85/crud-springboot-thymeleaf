package br.com.inicial.crud_sprinboot_thymeleaf.dto;

import javax.validation.constraints.NotBlank;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import lombok.Data;

@Data
public class UserEditDTO {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	private Categoria role = Categoria.DEFAULT;
	private Boolean enabled;
	
	public static UserEditDTO UserToUserProfileDTO(User user) {
		UserEditDTO userEditDTO = new UserEditDTO();
		userEditDTO.setId(user.getId());
		userEditDTO.setName(user.getName());
		userEditDTO.setEmail(user.getEmail());
		userEditDTO.setRole(user.getRole());
		userEditDTO.setEnabled(user.getEnabled());
		return userEditDTO;
	}

}
