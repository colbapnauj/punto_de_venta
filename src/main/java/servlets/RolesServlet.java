package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entidades.Rol;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.RolModelo;

@WebServlet("/RolesServlet")
@MultipartConfig
public class RolesServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public RolesServlet() {
        super();
    }
    
    public static final String ID = "idRol";
    
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

    	RolModelo model = new RolModelo();
		List<Rol> dataRoles= model.obtenerRoles();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			
			Gson gson = new Gson();
			String json= gson.toJson(dataRoles);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataRoles", dataRoles);
			req.getRequestDispatcher("mt-roles.jsp").forward(req, resp);	
		}
	}
    
    protected void registrar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nombre = req.getParameter("txtNombre");

		Rol rol= new Rol();
		rol.setNombre(nombre);

		RolModelo model = new RolModelo();
		int value = model.crearRol(rol);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-roles.jsp").forward(req, resp);
		}
	}
    
    protected void editar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);
		String nombre= req.getParameter("txtNombre");

		Rol rol = new Rol();
		rol.setIdRol(Integer.parseInt(id));
		rol.setNombre(nombre);
		
		RolModelo model = new RolModelo();
		int value = model.editarRol(rol);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-roles.jsp").forward(req, resp);
		}
	}
    
    protected void eliminar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);

		RolModelo model = new RolModelo();

		int value = model.eliminarRol(id);

		if (value != 1) {
			req.setAttribute("mensaje", "Ocurrió un problema al intentar eliminar registro estudiante con id " + id);
		} else {
			req.setAttribute("mensaje", "Registro con id " + id + " eliminado");			
		}

		configuracionInicial(req, resp);
	}
    
    protected void obtener(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);

		RolModelo model = new RolModelo();
		Rol rol = model.obtenerRol(id);

		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(rol);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataRol", rol);
			configuracionInicial(req, resp);			
		}
	}
}
