package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.MesaModelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entidades.Mesa;

@WebServlet("/MesasServlet")
@MultipartConfig
public class MesasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MesasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static boolean debugMode = false;

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");

		if (type == null) {
			configuracionInicial(req, resp);
			return;
		}

		switch (type) {

		case "load":
			configuracionInicial(req, resp);
			break;
		case "register": {
			String idProducto = req.getParameter("idMesa");
			if (idProducto == null) {
				// if (idProducto.isEmpty()) {
				registrar(req, resp);
			} else {
				editar(req, resp);
			}
		}
			break;
		case "delete":
			eliminar(req, resp);
			break;
		case "info":
			obtener(req, resp);
			break;
		default:
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mesas.jsp").forward(req, resp);
		}
	}
    
    protected void configuracionInicial(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    	MesaModelo model = new MesaModelo();
		List<Mesa> dataMesas= model.obtenerMesas();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			
			Gson gson = new Gson();
			String json= gson.toJson(dataMesas);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataMesas", dataMesas);
			req.getRequestDispatcher("mt-mesas.jsp").forward(req, resp);	
		}
	}
    
    protected void registrar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String descripcion = req.getParameter("txtDescripcion");

		Mesa mesa = new Mesa();
		mesa.setDescripcion(descripcion);

		MesaModelo model = new MesaModelo();
		int value = model.crearMesa(mesa);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-mesas.jsp").forward(req, resp);
		}
	}
    
    protected void editar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idMesa");
		String descripcion = req.getParameter("txtDescripcion");

		Mesa mesa = new Mesa();
		mesa.setIdMesa(Integer.parseInt(id));
		mesa.setDescripcion(descripcion);
		
		MesaModelo model = new MesaModelo();
		int value = model.editarMesa(mesa);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-mesas.jsp").forward(req, resp);
		}
	}
    
    protected void eliminar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idMesa");

		MesaModelo model = new MesaModelo();

		int value = model.eliminarMesa(id);

		if (value != 1) {
			req.setAttribute("mensaje", "Ocurrió un problema al intentar eliminar registro estudiante con id " + id);
			// TODO Es necesario un return;
		}

		req.setAttribute("mensaje", "Registro de producto con id " + id + " eliminado");
		configuracionInicial(req, resp);
	}
    
    protected void obtener(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idMesa");

		MesaModelo model = new MesaModelo();
		Mesa mesa = model.obtenerMesa(id);

		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(mesa);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataMesa", mesa);
			configuracionInicial(req, resp);			
		}

	}

}
