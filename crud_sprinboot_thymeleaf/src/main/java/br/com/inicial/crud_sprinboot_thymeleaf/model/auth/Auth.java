package br.com.inicial.crud_sprinboot_thymeleaf.model.auth;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.LoginDTO;
import lombok.Data;

@Data
public class Auth {
	
	private static final long MILLI_SECONDS =  10800000;
	
	private Long expiresIn;
	private Boolean active;
	
	public Auth(LoginDTO login) {
		this.expiresIn = System.currentTimeMillis() + MILLI_SECONDS;
		this.active = false;
	}
}
