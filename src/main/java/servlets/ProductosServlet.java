package servlets;

import java.io.IOException;
import java.util.List;

import entidades.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.ProductoModelo;

@WebServlet("/ProductoServlet")
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductosServlet() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String type = req.getParameter("type");
    	
    	if (type == null) {
    		configuracionInicial(req, resp);
    	}
    	
    	switch (type) {
		
		case "load": configuracionInicial(req, resp); break;
		case "register": {
			String idEstudiante = req.getParameter("idEstudiante");
			if (idEstudiante.isEmpty()) {
				registrarEstudiante(req, resp);
			} else {
				editarEstudiante(req, resp);
			}
		}; break;
		case "delete": eliminarEstudiante(req, resp); break;
		case "info": obtenerEstudiante(req, resp); break;
		default:
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("estudiante2.jsp").forward(req,  resp);
		}
    }
    
	protected void configuracionInicial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductoModelo model = new ProductoModelo();
		
		List<Producto> dataProductos = model.obtenerProductos();
		
		req.setAttribute("dataProductos", dataProductos);
		
		req.getRequestDispatcher("crudproductos.jsp").forward(req, resp);
	}
	
	protected void registrarEstudiante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	protected void editarEstudiante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void eliminarEstudiante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void obtenerEstudiante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
