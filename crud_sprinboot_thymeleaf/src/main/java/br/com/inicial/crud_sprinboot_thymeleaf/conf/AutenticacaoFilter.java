package br.com.inicial.crud_sprinboot_thymeleaf.conf;

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
import br.com.inicial.crud_sprinboot_thymeleaf.service.AuthService;

import static br.com.inicial.crud_sprinboot_thymeleaf.model.EndPoints.*;

@Component
@Order(1)
@WebFilter("/")
public class AutenticacaoFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse sevletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) sevletResponse;

		String endPoint = request.getRequestURI();

		// recebe o Usuario da sessão
		HttpSession sessao = request.getSession();

		UserProfileDTO usuarioLogado = (UserProfileDTO) sessao.getAttribute("userSession");
		request.setAttribute("userSession", usuarioLogado);

		boolean usuarioEstaLogado = (usuarioLogado != null);

		// paginas que podem ser acessada sem autenticação
		boolean urlProtegida = (endPoint.equals(ALL_USERS) || endPoint.equals(USER_STATUS_FALSE)
				|| endPoint.equals(USER_STATUS_TRUE) || endPoint.equals(USER_ROLE_ADM)
				|| endPoint.equals(USER_ROLE_DEFAULT) || endPoint.equals(ALL_DELECTED) || endPoint.equals(ALL_ACTIVED)
				|| endPoint.equals(USER_ID));

		if (urlProtegida && !usuarioEstaLogado) {
			request.setAttribute("mensagem", "Efetue o login para ter acesso");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}

		if (!usuarioEstaLogado && endPoint.equals("/")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}

		if (usuarioEstaLogado) {

			boolean altenticacaoExpirada = AuthService.expiredAuth(usuarioLogado.getAuth());

			// verifica se a autenticação esta expirada
			if (altenticacaoExpirada) {
				request.setAttribute("mensagem", "Autenticação expirada, realize o login");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
				dispatcher.forward(request, response);
			}
		}

		chain.doFilter(request, response);
	}
}