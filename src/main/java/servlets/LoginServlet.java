package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import entidades.Empleado;
import interfaces.AuthInterface;
import modelos.AuthModel;
import utils.Utils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		if (type.equals("login")) {
			
			String username = request.getParameter("txtUsername");
			String password = request.getParameter("txtPassword");
			
			AuthInterface model = new AuthModel();
			
			Empleado empleado = model.verificarInicioSesion(username, password);
			
			if (empleado != null) {
				SessionProject sessionProject = new SessionProject();
				sessionProject.saveSessionTimeOut(request, (60 * 60));
				sessionProject.saveSessionString(request, Constantes.ID_USER, empleado.getIdPersona() + "");
				sessionProject.saveSessionString(request, Constantes.NAME, empleado.getUsuario());
				sessionProject.saveSessionString(request, Constantes.USERNAME, empleado.getUsuario());
				sessionProject.saveSessionString(request, Constantes.ROL, empleado.getRol());
				
				System.out.println(sessionProject.getSessionString(request, Constantes.ID_USER));
				String projectPath = getServletContext().getContextPath();
				response.sendRedirect(projectPath + "/home");
				System.out.println("logeado");
			} else {
				request.setAttribute("mensaje", "Credenciales incorrectas");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				System.out.println("error");
			}
		} else if (type.equals("logout")) {
			
			SessionProject sessionProject = new SessionProject();
			sessionProject.invalidateSession(request);
			String projectPath = getServletContext().getContextPath();
			response.sendRedirect(projectPath + "/");
		} else if (type.equals("create")) {
			
			String password = request.getParameter("txtPassword");
			
			AuthModel model= new AuthModel();
			String hashedPassword = Utils.hashPassword(password);
			System.out.println(hashedPassword);
			response.sendRedirect("index.jsp");
			
		}
	}
}


