package br.com.inicial.crud_sprinboot_thymeleaf.util;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.inicial.crud_sprinboot_thymeleaf.CrudSprinbootThymeleafApplication;
import br.com.inicial.crud_sprinboot_thymeleaf.model.User;

public class Builder extends CrudSprinbootThymeleafApplication{
	
	private static Faker faker = Faker.instance(new Locale("pt", "BR"));
	
	public static User userForTest() {
		User user = new User();
		user.setName(faker.name().fullName());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword(CriptografiaPassword.encryptMD5("asdf"));
		user.setEnabled(Boolean.TRUE);
		return user;
	}
}
