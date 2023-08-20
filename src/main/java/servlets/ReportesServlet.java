package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ReporteInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.ReporteModel;

import java.util.List;

import com.google.gson.Gson;

import entidades.ComprobanteDePago;

@WebServlet("/reportes")
@MultipartConfig
public class ReportesServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
  
  private static final boolean  debugMode = false;
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String type = req.getParameter("type");
    
    if (type == null) {
      reporteVentas(req, resp);
      return;
    }
    
    switch (type) {
    case "ventas": 
      reporteVentas(req, resp);
      break;
    default:
      resp.sendRedirect(getServletContext().getContextPath() + "/home");
    }
  }
  
  private void reporteVentas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String fechaInicio = req.getParameter("fechaInicio");
    String fechaFin = req.getParameter("fechaFin");
    
    // Validación de fechas con regex
    String formatoFechaRegex = "\\d{4}-\\d{2}-\\d{2}";
    
    if (fechaInicio == null || !fechaInicio.matches(formatoFechaRegex)) {
      req.setAttribute("mensaje", "Formato de fecha de inicio nulo o inválida");
      req.getRequestDispatcher("/reportes/index.jsp").forward(req, resp);
      return;
    }
    
    if (fechaFin == null || !fechaFin.matches(formatoFechaRegex)) {
      req.setAttribute("mensaje", "Formato de fecha de fin nulo o inválida");
      req.getRequestDispatcher("/reportes/index.jsp").forward(req, resp);
      return;
    }
        
    ReporteInterface model = new ReporteModel();
    
    List<ComprobanteDePago> listaComprobantes = model.obtenerComprobantes(fechaInicio, fechaFin);
    
    if (debugMode) {
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      
      Gson gson = new Gson();
      String json = gson.toJson(listaComprobantes);
      PrintWriter out = resp.getWriter();
      out.write(json);
      return;
    }
    
    req.setAttribute("listaComprobantes", listaComprobantes);
    req.getRequestDispatcher("/reportes/index.jsp").forward(req, resp);
  }
  
  
}
