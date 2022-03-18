package br.com.inicial.crud_sprinboot_thymeleaf.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.inicial.crud_sprinboot_thymeleaf.CrudSprinbootThymeleafApplicationTests;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.LoginDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserProfileDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.util.Builder;

public class UserTest extends CrudSprinbootThymeleafApplicationTests{
	
	private User user;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthService authService;
	
	@BeforeEach
	public void firstOfAll() {
		user = Builder.userForTest();
	}
	@AfterEach
	public void afterOfAll() {
		service.deleteById(user.getId());
	}
	@Test
	public void insertUser() {
		service.save(user);
		assertTrue(user.getId() != null);
	}
	@Test
	public void listAll() {
		service.save(user);
		List<User>users = service.findAll();
		assertTrue(users.size() > 0);
	}
	@Test
	public void listAllActived() {
		user.setEnabled(true);
		service.save(user);
		List<User>users = service.fingByEnabled(true);
		assertTrue(users.size() > 0);
		Optional<User> userPersisted = service.findById(user.getId());
		assertTrue(userPersisted.get().getEnabled() == true);
	}
	@Test
	public void listAllRole_ADM() {
		user.setRole(Categoria.ADM);
		service.save(user);
		List<User>users = service.fingByRole(Categoria.ADM);
		assertTrue(users.size() > 0);
		Optional<User> userPersisted = service.findById(user.getId());
		assertTrue(userPersisted.get().getRole() == Categoria.ADM);
	}
	@Test
	public void getUserById() {
		service.save(user);
		Optional<User> userGetById = service.findById(user.getId());
		assertTrue(user.equals(userGetById.get()));
		
	}
	@Test
	public void update() {
		service.save(user);
		Optional<User> updateUser = service.findById(user.getId());
		updateUser.get().setName(faker.name().fullName());
		service.update(updateUser.get());
		assertTrue(updateUser.get().getId() == user.getId());
	} 
	
	@Test 
	public void authExpired() {
		service.save(user);
		LoginDTO login = new LoginDTO(user.getEmail(), "asdf");
		UserProfileDTO userAuth = authService.login(login);
		assertTrue(userAuth.getName().equals(user.getName()));
		boolean expired = AuthService.expiredAuth(userAuth.getAuth());
		assertTrue(!expired);
		userAuth.getAuth().setExpiresIn(0L);
		boolean expired2 = AuthService.expiredAuth(userAuth.getAuth());
		assertTrue(expired2);
		assertTrue(!userAuth.getAuth().getActive());
	}
}
