package br.com.inicial.crud_sprinboot_thymeleaf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/","/formCadastro","h2", "/h2/login.do?jsessionid=83445dd5f79409b0f8f05a067be6d1bd", "/usuario/logando", "/usuario/addUsuario").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login").permitAll();
	}

}
