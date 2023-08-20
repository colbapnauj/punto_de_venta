package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
@MultipartConfig
public class FacturacionServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String idPedido = req.getParameter("idPedido");
		
		if (type == null || idPedido == null) {
			resp.sendRedirect(getServletContext().getContextPath() + "/home");
			return;
		}
		
		switch (type) {
		case "cargarPedido":
			cargarPedido(req, resp);
			break;
		case "crearComprobante":
			crearComprobante(req, resp);
			break;
		default:
			resp.sendRedirect(getServletContext().getContextPath() + "/home");
		} 
	}
	
	protected void cargarPedido(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void crearComprobante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
