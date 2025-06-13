package br.com.luanderson.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luanderson.todolist.user.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TaskAuthFilter extends OncePerRequestFilter {

	@Autowired
	private IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var servletPath = request.getServletPath();

		if (servletPath.startsWith("/tasks")) {
			var authorization = request.getHeader("Authorization");

			if (authorization == null || !authorization.startsWith("Basic ")) {
				response.sendError(401, "Authorization header missing or invalid");
				return;
			}

			try {
				var authEncoded = authorization.substring("Basic".length()).trim();
				byte[] authDecode = Base64.getDecoder().decode(authEncoded);
				var authString = new String(authDecode);
				String[] credentials = authString.split(":", 2);
				if (credentials.length != 2) {
					response.sendError(401, "Invalid basic authentication format");
					return;
				}
				String username = credentials[0];
				String password = credentials[1];

				var user = this.userRepository.findByUsername(username);
				if (user.isEmpty()) {
					response.sendError(401, "Usuário não encontrado");
					return;
				}
				var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword());
				if (passwordVerify.verified) {
					request.setAttribute("id", user.get().getId());
					filterChain.doFilter(request, response);
				} else {
					response.sendError(401, "Senha inválida");
				}
			} catch (Exception e) {
				response.sendError(401, "Erro ao processar autenticação");
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
