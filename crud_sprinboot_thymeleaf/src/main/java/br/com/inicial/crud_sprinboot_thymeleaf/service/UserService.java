package br.com.inicial.crud_sprinboot_thymeleaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User save(User user) {
		return repository.save(user);
	}

	public Optional<User> fingByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public List<User> fingByEnabled(boolean enabled) {
		List<User> usersEnabled = repository.findByEnabled(enabled);
		if (usersEnabled == null) {
			usersEnabled = new ArrayList<>();
		}
		return usersEnabled;
	}
	
	public List<User> fingByRole(Categoria role) {
		List<User> usersRole = repository.findByRole(role);
		if (usersRole == null) {
			usersRole = new ArrayList<>();
		}
		return usersRole;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	public void update(User user) {
		repository.save(user);
	}
}
