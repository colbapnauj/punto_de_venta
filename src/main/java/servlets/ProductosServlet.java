package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entidades.Producto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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

		req.setAttribute("dataProductos", dataProductos);
		req.getRequestDispatcher("pedido.jsp").forward(req, resp);
	}

	protected void registrarProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String descripcion = req.getParameter("txtDescripcion");
		String precio = req.getParameter("txtPrecio");
		// temp
		// String foto = req.getParameter("txtFoto");

		Producto producto = new Producto();
		producto.setDescripcion(descripcion);
		producto.setPrecio(Double.parseDouble(precio));
		// TODO
		// producto.setFoto(foto);

		// TODO
		/// Grabar foto de producto
//		Part filePart = req.getPart("file");
//	    String fileName = filePart.getSubmittedFileName();
//	    for (Part part : req.getParts()) {
//	      part.write("C:\\upload\\" + fileName);
//	    }
//	    resp.getWriter().print("Archivo cargado correctamente.");
//	    producto.setFoto(fileName);

		ProductoModelo model = new ProductoModelo();
		int value = model.crearProducto(producto);

//		resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");

		// Envía la respuesta JSON al cliente.
		// PrintWriter out = resp.getWriter();

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
		int value = model.eliminarProducto(Integer.parseInt(id));

		if (value != 1) {
			req.setAttribute("mensaje", "Ocurrió un problema al intentar eliminar registro estudiante con id " + id);
		}

		req.setAttribute("mensaje", "Registro de producto con id " + id + " eliminado");
		configuracionInicial(req, resp);

	}

	protected void obtenerProducto(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("idProducto");

		ProductoModelo model = new ProductoModelo();
		Producto producto = model.obtenerProducto(Integer.parseInt(id));

		req.setAttribute("dataProducto", producto);
		configuracionInicial(req, resp);

	}

}
