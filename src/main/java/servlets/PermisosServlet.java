package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entidades.Permiso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.PermisoModelo;

@WebServlet("/PermisosServlet")
@MultipartConfig
public class PermisosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PermisosServlet() {
        super();
    }
    
    public static final String ID = "idPermiso";
    
    private static boolean debugMode = true;

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
			String idProducto = req.getParameter(ID);
			if (idProducto == null) {
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

    	PermisoModelo model = new PermisoModelo();
		List<Permiso> dataPermisos= model.obtenerPermisos();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			
			Gson gson = new Gson();
			String json= gson.toJson(dataPermisos);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataPermisos", dataPermisos);
			req.getRequestDispatcher("mt-permisos.jsp").forward(req, resp);	
		}
	}
    
    protected void registrar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nombre = req.getParameter("txtNombre");

		Permiso permiso = new Permiso();
		permiso.setNombre(nombre);

		PermisoModelo model = new PermisoModelo();
		int value = model.crearPermiso(permiso);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-permisos.jsp").forward(req, resp);
		}
	}
    
    protected void editar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);
		String nombre = req.getParameter("txtNombre");

		Permiso permiso = new Permiso();
		permiso .setIdPermiso(Integer.parseInt(id));
		permiso .setNombre(nombre);
		
		PermisoModelo model = new PermisoModelo();
		int value = model.editarPermiso(permiso);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-permisos.jsp").forward(req, resp);
		}
	}
    
    protected void eliminar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);

		PermisoModelo model = new PermisoModelo();

		int value = model.eliminarPermiso(id);

		if (value != 1) {
			req.setAttribute("mensaje", "Ocurrió un problema al intentar eliminar registro con id " + id);
		} else {
			req.setAttribute("mensaje", "Registro con id " + id + " eliminado");			
		}

		configuracionInicial(req, resp);
	}
    
    protected void obtener(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);

		PermisoModelo model = new PermisoModelo();
		Permiso rol = model.obtenerPermiso(id);

		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(rol);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataPermiso", rol);
			configuracionInicial(req, resp);			
		}
	}
}
