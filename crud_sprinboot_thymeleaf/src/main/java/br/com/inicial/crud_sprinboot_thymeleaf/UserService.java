package br.com.inicial.crud_sprinboot_thymeleaf;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.inicial.crud_sprinboot_thymeleaf.model.User;
import br.com.inicial.crud_sprinboot_thymeleaf.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

}
