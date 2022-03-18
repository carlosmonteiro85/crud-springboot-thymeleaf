package br.com.inicial.crud_sprinboot_thymeleaf.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.inicial.crud_sprinboot_thymeleaf.CrudSprinbootThymeleafApplicationTests;
import br.com.inicial.crud_sprinboot_thymeleaf.UserService;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;

public class UserServiceTest extends CrudSprinbootThymeleafApplicationTests{
	
	@Autowired
	private UserService service;
	
	@Test
	public void createUser() {
		// Cenario
		User user = createForTest();
		// Teste
		service.save(user);
		//Verificação
		assertTrue(user.getId() != null);
	}
	
	
	
	private User createForTest() {
		User user = new User();
		user.setName(faker.name().name());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword("senha");
		return user;
	}

}
