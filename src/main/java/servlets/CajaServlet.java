package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entidades.ComprobanteDePago;
import entidades.Pedido;
import interfaces.CajaInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.CajaModel;
import modelos.PedidoModelo;

@WebServlet("/caja")
@MultipartConfig
public class CajaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static final boolean  debugMode = false;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		
		if (type == null) {
			cargarPedidos(req, resp);
			return;
		}
		
		switch (type) {
		case "cargarPedidos": {
		  cargarPedidos(req, resp);
		  break;
		}
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
	
	protected void cargarPedidos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  PedidoModelo model = new PedidoModelo();
    
    List<Pedido> listaPedidos = model.obtenerPedidos();
    req.setAttribute("listaPedidos", listaPedidos);
    
    if (debugMode) {
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      
      Gson gson = new Gson();
      String json = gson.toJson(listaPedidos);
      PrintWriter out = resp.getWriter();
      out.write(json);
      return;
    }
    
    req.getRequestDispatcher("caja.jsp").forward(req, resp);
	}
	
	protected void cargarPedido(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  PedidoModelo model = new PedidoModelo();
	  
	  String idPedido = req.getParameter("idPedido");
	  String idUsuario = req.getParameter("idUsuario");
	  Pedido dataPedido = model.obtenerPedidoConDetalle(Integer.parseInt(idPedido), Integer.parseInt(idUsuario));
	  req.setAttribute("dataPedido", dataPedido);
	  
	  if (debugMode) {
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      
      Gson gson = new Gson();
      String json = gson.toJson(dataPedido);
      PrintWriter out = resp.getWriter();
      out.write(json);
      return;
    }
	  
	  cargarPedidos(req, resp);
	}
	
	protected void crearComprobante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  
	  int idPedido = Integer.parseInt(req.getParameter("idPedido"));
	  
	  CajaInterface model = new CajaModel();
	  double totalAPagar = model.obtenerTotalDePedido(idPedido);
	  
	  ComprobanteDePago comprobante = new ComprobanteDePago();
	  comprobante.setIdPedido(idPedido);
	  comprobante.setTotal(totalAPagar);
		
		boolean success = model.crearComprobanteDePago(comprobante);
		
		String mensaje = success ? "Comprobante Generado" : "No se pudo generar el comprbante";
		req.setAttribute("mensaje", mensaje);
		
		cargarPedidos(req, resp);
	}

}
