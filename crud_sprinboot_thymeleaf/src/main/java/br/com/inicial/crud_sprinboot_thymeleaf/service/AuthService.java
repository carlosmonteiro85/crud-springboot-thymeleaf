package br.com.inicial.crud_sprinboot_thymeleaf.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.LoginDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserProfileDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.model.auth.Auth;
import br.com.inicial.crud_sprinboot_thymeleaf.repository.UserRepository;
import br.com.inicial.crud_sprinboot_thymeleaf.util.CriptografiaPassword;

@Service
public class AuthService {

	private UserRepository repository;

	public AuthService(UserRepository repository) {
		this.repository = repository;
	}
	
	public static Boolean expiredAuth(Auth auth) {
		
		Long currentTime = System.currentTimeMillis();
		if (auth.getExpiresIn() - currentTime < 0) {
			auth.setActive(false);
			return true;
		}
		return false;
	}

	public UserProfileDTO login(@Valid LoginDTO login) {
		Optional<User> user = null;
		UserProfileDTO userSession = null ;
		Auth auth = null;
		try {
			user = repository.findByEmail(login.getEmail());
			String password = CriptografiaPassword.encryptMD5(login.getPassword());
			if (user.isPresent() && user.get().getPassword().equals(password)) {
				auth = new Auth(login);
				auth.setActive(true);				
				userSession = UserProfileDTO.UserToUserProfileDTO(user.get());
				userSession.setAuth(auth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userSession;
	}
	

}
