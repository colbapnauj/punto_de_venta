package servlets;

import java.io.IOException;
import java.lang.reflect.Type;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import interfaces.PedidoInterface;
import modelos.PedidoModelo;
import entidades.Pedido;
import entidades.ProductoCantidad;

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
		case "load": {
			if (req.getParameter("idPedido") != null) {
				cargarPedido(req, resp);
			}else {
				cargarPedidos(req, resp);
			}
		}
			break;
		case "crearPedido":
			crearPedido(req, resp);
			break;
		default:
			req.getRequestDispatcher("pedidos.jps").include(req, resp);
		} 
			
	}
	
	private void cargarPedidos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PedidoModelo model = new PedidoModelo();
		
		List<Pedido> listaPedidos = model.obtenerPedidos();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(listaPedidos);
			PrintWriter out = resp.getWriter();
			out.write(json);
		}
	}
	
	private void cargarPedido(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idPedido = req.getParameter("idPedido");
		
		// TODO get idUsuario de la sesión
		
		PedidoModelo model = new PedidoModelo();
		
		Pedido pedido = model.obtenerPedidoConDetalle(Integer.parseInt(idPedido), 1);
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(pedido);
			PrintWriter out = resp.getWriter();
			out.write(json);
		}
	}
	
	private void crearPedido(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jsonString = req.getParameter("data");
		
		List<ProductoCantidad> productos = obtenerProductos(jsonString);
		
		try {
			SessionProject sessionProject = new SessionProject();
			String idUser = (String) sessionProject.getSessionString(req, Constantes.ID_USER);
			
			int idUsuario = Integer.parseInt(idUser);
			int idMesa = Integer.parseInt(req.getParameter("idMesa"));
			
			productos.add(new ProductoCantidad(3, 2));
			
			PedidoInterface model = new PedidoModelo();
			
			boolean success = model.crearPedido(idUsuario, idMesa, productos);
			
			if (debugMode) {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				
				Gson gson = new Gson();
				String json = gson.toJson(success);
				PrintWriter out = resp.getWriter();
				out.write(json);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<ProductoCantidad> obtenerProductos(String jsonString) {
		
		Gson gson = new GsonBuilder().create();
		
		Type detallesListType = new TypeToken<List<ProductoCantidad>>() {}.getType();
		
		List<ProductoCantidad> productos = gson.fromJson(jsonString, detallesListType);
		
		return productos;
	}
	
			

}
