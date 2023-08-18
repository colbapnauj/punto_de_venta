package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import entidades.Permiso;
import entidades.RolTienePermiso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.RolTienePermisoModelo;

@WebServlet("/RolesTienenPermisosServlet")
@MultipartConfig
public class RolTienePermisosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		case "actualizar": 
			actualizar(req, resp);
			break;
		case "info":
			obtener(req, resp);
			break;
		default:
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mt-roltienepermisos.jsp").forward(req, resp);
		}
    }
    
    protected void actualizar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    	String id = req.getParameter("idRol");
		String[] permisosStr = req.getParameterValues("idPermiso");
		
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		
//		Gson gson = new Gson();
//		String json = gson.toJson(permisosStr);
//		PrintWriter out = resp.getWriter();
//		out.write(json);
		
		if (permisosStr != null) {
			List<Integer> permisos = new ArrayList<>();
			for (String idPermisoStr : permisosStr) {
				permisos.add(Integer.parseInt(idPermisoStr));
			}
			
			RolTienePermisoModelo model = new RolTienePermisoModelo();
			model.actualizarPermisosDeRol(Integer.parseInt(id), permisos);
			// TODO Manejar errores
			
			// obtener(req, resp);
			
		} else {
			
			req.setAttribute("mensaje", "Seleccione al menos un permiso");
			// req.getRequestDispatcher("mt-roltienepermisos.jsp").forward(req, resp);
		}
		
		obtener(req, resp);
	}
    
    protected void obtener(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);

		RolTienePermisoModelo model = new RolTienePermisoModelo();
		List<Permiso> permisos = model.obtenerPermisosSegunRol(id);

		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json = gson.toJson(permisos);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataPermisosSegunRol", permisos);
			configuracionInicial(req, resp);			
		}
	}
    
    protected void configuracionInicial(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    	RolTienePermisoModelo model = new RolTienePermisoModelo();
		List<RolTienePermiso> dataRolTienePermisos= model.obtenerRolesYPermisos();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			
			Gson gson = new Gson();
			String json= gson.toJson(dataRolTienePermisos);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataRolTienePermisos", dataRolTienePermisos);
			req.getRequestDispatcher("mt-roltienepermisos.jsp").forward(req, resp);	
		}
	}

}
