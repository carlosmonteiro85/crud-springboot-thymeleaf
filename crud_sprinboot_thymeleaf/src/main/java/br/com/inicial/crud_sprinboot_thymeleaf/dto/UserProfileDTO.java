package br.com.inicial.crud_sprinboot_thymeleaf.dto;

import javax.validation.constraints.NotBlank;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.model.auth.Auth;
import br.com.inicial.crud_sprinboot_thymeleaf.util.CriptografiaPassword;
import lombok.Data;

@Data
public class UserProfileDTO {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String confirmPass;
	@NotBlank
	private String email;
	private Categoria role = Categoria.DEFAULT;
	private Boolean enabled;
	private Auth auth;

	public static UserProfileDTO UserToUserProfileDTO(User user) {
		UserProfileDTO dto = new UserProfileDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setEnabled(user.getEnabled());
		dto.setRole(user.getRole());
		dto.setPassword(CriptografiaPassword.encryptMD5(user.getPassword()));
		return dto;
	}

	public static User UserProfileDTOtoUser_ForAdm(UserProfileDTO userProfileDTO) {
		User user = new User();
		user.setId(userProfileDTO.getId());
		user.setName(userProfileDTO.getName());
		user.setEmail(userProfileDTO.getEmail());
		user.setEnabled(userProfileDTO.getEnabled());
		user.setRole(userProfileDTO.getRole());
		return user;
	}

	public static User UserProfileDTOtoUser_profile(UserProfileDTO userProfileDTO) {
		User user = new User();
		user.setId(userProfileDTO.getId());
		user.setName(userProfileDTO.getName());
		user.setEmail(userProfileDTO.getEmail());
		user.setPassword(userProfileDTO.getPassword());
		user.setEnabled(userProfileDTO.getEnabled());
		user.setRole(userProfileDTO.getRole());

		return user;
	}

}
