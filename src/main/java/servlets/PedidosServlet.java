package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import modelos.PedidoModelo;
import entidades.Pedido;
import entidades.Mesa;

@WebServlet("/PedidosServlet")
@MultipartConfig
public class PedidosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PedidosServlet() {
		super();
	}
	
	private static boolean debugMode = false;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		
		if (type == null) {
			cargarPedidos(req, resp);
			return;
		}
		
		switch (type) {
		case "load":
			cargarPedidos(req, resp);
			break;
		case "register":
			registrar(req, resp);
			break;
		default:
			req.getRequestDispatcher("pedidos.jps").include(req, resp);
		} 
			
	}
	
	private void cargarPedidos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PedidoModelo model = new PedidoModelo();
		
		Pedido pedido = model.obtenerPedidoConDetalle(1, 1);
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(pedido);
			PrintWriter out = resp.getWriter();
			out.write(json);
		}
		
	}
	
	private void registrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
			

}
