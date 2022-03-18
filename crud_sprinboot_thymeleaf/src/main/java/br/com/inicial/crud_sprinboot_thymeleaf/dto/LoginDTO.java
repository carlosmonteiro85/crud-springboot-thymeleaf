package br.com.inicial.crud_sprinboot_thymeleaf.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
