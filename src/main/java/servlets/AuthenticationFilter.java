package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String path = httpRequest.getServletPath();
		System.out.println(path);
		
		if (isPublicResource(httpRequest) || isLoggedIn(httpRequest)) {
		// if (isLoggedIn(httpRequest)) {
			chain.doFilter(request, response);
			System.out.println("next");
		} else {
			// httpRequest.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
			System.out.println("redireccionado");
			httpResponse.sendRedirect("index.jsp");
		}
		
	}
	
	private boolean isPublicResource(HttpServletRequest request) {
		String path = request.getServletPath();
		List<String> whiteList = Arrays.asList("/index.jsp", "/LoginServlet");
		
		return whiteList.contains(path) || path.startsWith("/static/");
	}
	
	private boolean isLoggedIn(HttpServletRequest request) {
		SessionProject sessionProject = new SessionProject();
		System.out.println(sessionProject.getSessionString(request, Constantes.ID_USER));
		return (String) sessionProject.getSessionString(request, Constantes.ID_USER) != null;
	}
	
}
