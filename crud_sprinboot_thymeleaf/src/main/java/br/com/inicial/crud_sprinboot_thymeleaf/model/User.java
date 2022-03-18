package br.com.inicial.crud_sprinboot_thymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "nome")
	private String name;
	@Column(nullable = false, name = "senha")
	private String password;
	@Column(nullable = false, unique = true, name = "email")
	private String email;
	@Enumerated(EnumType.STRING)
	private Categoria role = Categoria.DEFAULT;
	@Column(name = "ativo")
	private Boolean enabled = false;
}