package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entidades.Producto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelos.ProductoModelo;
import java.util.UUID;

import com.google.gson.Gson;

@WebServlet("/ProductoServlet")
@MultipartConfig
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductosServlet() {
		super();
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
			String idProducto = req.getParameter("idProducto");
			if (idProducto == null) {
				// if (idProducto.isEmpty()) {
				registrarProducto(req, resp);
			} else {
				editarProducto(req, resp);
			}
		}
			;
			break;
		case "delete":
			eliminarProducto(req, resp);
			break;
		case "info":
			obtenerProducto(req, resp);
			break;
		default:
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("mesas.jsp").forward(req, resp);
		}
	}

	protected void configuracionInicial(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProductoModelo model = new ProductoModelo();
		List<Producto> dataProductos = model.obtenerProductos();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			
			Gson gson = new Gson();
			String json= gson.toJson(dataProductos);
			PrintWriter out = resp.getWriter();
			out.write(json);
		} else {
			req.setAttribute("dataProductos", dataProductos);
			req.getRequestDispatcher("pedido.jsp").forward(req, resp);	
		}

	}

	protected void registrarProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String descripcion = req.getParameter("txtDescripcion");
		String precio = req.getParameter("txtPrecio");

		Producto producto = new Producto();
		producto.setDescripcion(descripcion);
		producto.setPrecio(Double.parseDouble(precio));

		/// Grabar foto de producto
		String fileName = cargarArchivo(req, resp);
		producto.setFoto(fileName);

		ProductoModelo model = new ProductoModelo();
		int value = model.crearProducto(producto);

		if (value == 1) {
			// TODO clear register
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("configuracion.jsp").forward(req, resp);
		}	

		
	}

	protected void editarProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Entradas
		String id = req.getParameter("idProducto");
		String descripcion = req.getParameter("txtDescripcion");
		String precio = req.getParameter("txtPrecio");

		// Creamos objecto
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(id));
		producto.setDescripcion(descripcion);
		producto.setPrecio(Double.parseDouble(precio));
		
		// Procesos
		ProductoModelo model = new ProductoModelo();
		
		String fileName = cargarArchivo(req, resp);
		if (fileName != null) {
			Producto oldProducto = model.obtenerProducto(Integer.parseInt(id));
			if (producto != null) {
				String oldFileName = oldProducto.getFotoRawName();
				if (oldFileName != null) {
					eliminarArchivo(oldFileName);
				}
			}
			
			producto.setFoto(fileName);
		}

		
		int value = model.editarProducto(producto);

		if (value == 1) {
			configuracionInicial(req, resp);
		} else {
			req.setAttribute("mensaje", "Ocurrió un problema");
			req.getRequestDispatcher("configuracion.jsp").forward(req, resp);
		}

	}

	protected void eliminarProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idProducto");

		ProductoModelo model = new ProductoModelo();
		
		// eliminar foto de producto
		Producto producto = model.obtenerProducto(Integer.parseInt(id));
		if (producto != null) {
			String fileName = producto.getFotoRawName();
			if (fileName != null) {
				eliminarArchivo(fileName);
			}
		}
		// end: eliminar foto
		

		int value = model.eliminarProducto(Integer.parseInt(id));

		if (value != 1) {
			req.setAttribute("mensaje", "Ocurrió un problema al intentar eliminar registro estudiante con id " + id);
			// TODO Es necesario un return;
		}

		req.setAttribute("mensaje", "Registro de producto con id " + id + " eliminado");
		configuracionInicial(req, resp);

	}

	protected void obtenerProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idProducto");

		ProductoModelo model = new ProductoModelo();
		Producto producto = model.obtenerProducto(Integer.parseInt(id));

		if (debugMode) {
			// Configurar la respuesta
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			// Envía la respuesta JSON al cliente.
			Gson gson = new Gson();
			String productoJson = gson.toJson(producto);
			PrintWriter out = resp.getWriter();
			out.write(productoJson);
		} else {
			req.setAttribute("dataProducto", producto);
			configuracionInicial(req, resp);			
		}

	}
	
	private String cargarArchivo(HttpServletRequest req, HttpServletResponse resp) {
		
		/// Grabar foto de producto
		String fileName = null;
		try {
			// guard clouse
			if (req.getPart("file") == null) {
				return null;
			}
			
			Part filePart = req.getPart("file");
		    // String fileName = filePart.getSubmittedFileName();
			// Se le agrega \\. porque split espera una expresión regular y enviar un punto solo causa error
			String[] fileNameArray = filePart.getSubmittedFileName().split("\\.");
			String extension = fileNameArray[fileNameArray.length - 1];
			// Obtener nombre random
		    fileName = UUID.randomUUID().toString() + "." + extension;
		    
		    // Obtener ruta y concatener nombre de carpeta para archivos
		    String uploadPath = getServletContext().getRealPath("/") + "upload/";
			filePart.write(uploadPath + fileName);
			
		    resp.getWriter().print("Archivo cargado correctamente.\n");
		    System.out.println(fileName);
		    return fileName;

		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return fileName;
	}
	
	private void eliminarArchivo(String fileName) {
	    String uploadPath = getServletContext().getRealPath("/") + "upload/";
	    File file = new File(uploadPath + fileName);
	    if (file.exists()) {
	        file.delete();
	    }
	}

}
