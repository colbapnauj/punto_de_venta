package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entidades.Mesa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.PedidoModelo;

@WebServlet("/home")
@MultipartConfig
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static boolean debugMode = false;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		
		if (type == null) {
			cargarMesas(req, resp);
			return;
		}
		
		switch (type) {
		case "obtenerMesas":
			cargarMesas(req, resp);
			break;
		default:
			req.getRequestDispatcher("mesas.jps").include(req, resp);
		} 
	}
	
	private void cargarMesas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		PedidoModelo model = new PedidoModelo();
		List<Mesa> listaMesas = model.obtenerMesas();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(session);
			PrintWriter out = resp.getWriter();
			out.write(json);
			return;
		}
		
		req.setAttribute("listaMesas", listaMesas);
		req.getRequestDispatcher("mesas.jsp").include(req, resp);
	}

}
