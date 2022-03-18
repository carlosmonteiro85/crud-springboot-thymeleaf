package br.com.inicial.crud_sprinboot_thymeleaf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u where u.email = :email ")
	Optional<User> findByEmail(@Param("email") String email);
	
	@Query("select u from User u where u.enabled = :enabled")
	List<User> findByEnabled(@Param("enabled") Boolean enabled);
	
	@Query("select u from User u where u.role = :role")
	List<User> findByRole(@Param("role") Categoria role);
	
}
