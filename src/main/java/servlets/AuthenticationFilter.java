package servlets;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// chain.doFilter(request, response);
		
		if (isPublicResource(httpRequest) || isLoggedIn(httpRequest)) {
			chain.doFilter(request, response);
			System.out.println("next");
		} else {
			httpResponse.sendRedirect("index.jsp");
			System.out.println("redireccionado");
		}
	}
	
	private boolean isPublicResource(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.equals("index.jsp") || path.startsWith("/static/");
	}
	
	private boolean isLoggedIn(HttpServletRequest request) {
		SessionProject sessionProject = new SessionProject();
		return sessionProject.getSessionString(request, Constantes.USERNAME) != null;
	}
	
}
