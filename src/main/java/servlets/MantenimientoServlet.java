package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import com.google.gson.Gson;

import entidades.Empleado;
import interfaces.UsuarioInterface;
import modelos.UsuarioModelo;

@WebServlet("/mantenimiento/*")
public class MantenimientoServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String pathInfo = request.getPathInfo();
    
    if (pathInfo == null) {
      // TODO
    }
    
    switch (pathInfo) {
    case "/usuarios": {
      if (request.getParameter("type") == null) {
        loadConfigUsuarios(request, response);        
      } else if (request.getParameter("type") == "registro") {
        registrarUsuario(request, response);
      }
      break;
      }
    }
  }
  
  protected void loadConfigUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Devolver usuarios
    // TODO Devolver roles
    
    UsuarioInterface model = new UsuarioModelo();
    List<Empleado> listaEmpleados =  model.obtenerEmpleados();
    
    request.setAttribute("listaEmpleado", listaEmpleados);
    
    response.setContentType("application/json");
    request.setCharacterEncoding("UTF-8");
    
    Gson gson = new Gson();
    String json = gson.toJson(listaEmpleados);
    PrintWriter out = response.getWriter();
    out.write(json);
    
    
  }
  
  protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
  }

}
