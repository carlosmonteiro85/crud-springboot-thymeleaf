package br.com.inicial.crud_sprinboot_thymeleaf;

import java.util.Locale;

import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

@SpringBootTest
public class CrudSprinbootThymeleafApplicationTests {

	protected static Faker faker = Faker.instance(new Locale("pt", "BR"));

}
