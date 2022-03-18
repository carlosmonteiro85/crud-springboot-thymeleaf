package br.com.inicial.crud_sprinboot_thymeleaf.conf;

import static br.com.inicial.crud_sprinboot_thymeleaf.model.EndPoints.*;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.inicial.crud_sprinboot_thymeleaf.dto.UserProfileDTO;
import br.com.inicial.crud_sprinboot_thymeleaf.model.Categoria;

@Component
@Order(2)
@WebFilter("/")
public class AutorizacaoFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse sevletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) sevletResponse;

		String endPoint = request.getRequestURI();

		HttpSession sessao = request.getSession();
		UserProfileDTO usuarioLogado = (UserProfileDTO) sessao.getAttribute("userSession");
		boolean usuarioEstaLogado = (usuarioLogado != null);

		boolean endPointProtegido = (endPoint.equals(ALL_USERS) || endPoint.equals(USER_STATUS_FALSE)
				|| endPoint.equals(USER_STATUS_TRUE) || endPoint.equals(USER_ROLE_ADM)
				|| endPoint.equals(USER_ROLE_DEFAULT) || endPoint.equals(ALL_DELECTED) || endPoint.equals(ALL_ACTIVED)
				|| endPoint.equals(USER_ID));

		if (usuarioEstaLogado) {
			if (endPointProtegido && usuarioLogado.getRole().equals(Categoria.DEFAULT)) {
				request.setAttribute("mensagem", "Sem permissão para esse acesso");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
				dispatcher.forward(request, response);
			}
			request.setAttribute("userSession", usuarioLogado);
		}

		chain.doFilter(request, response);
	}
}