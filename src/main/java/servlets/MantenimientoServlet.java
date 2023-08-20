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
import entidades.Rol;
import interfaces.RolInterface;
import interfaces.UsuarioInterface;
import modelos.RolModelo;
import modelos.UsuarioModelo;

@WebServlet("/mantenimiento/*")
public class MantenimientoServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  
  private static final boolean  debugMode = true;
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String pathInfo = request.getPathInfo();
    System.out.println(pathInfo);
    
    String type = request.getParameter("type");
    
    if (pathInfo == null) {
      // TODO
    }
    
    switch (pathInfo) {
    case "/usuarios": {
      if (type == null) {
        loadConfigUsuarios(request, response);        
      } else if (type.equals("registro")) {
          if (request.getParameter("idPersona") != null) {
            editarUsuario(request, response);            
          } else {
            registrarUsuario(request, response);            
          }
      } 

    break;
    }
    default:
      loadConfigUsuarios(request, response);
    }
  }
  
  protected void loadConfigUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // [X] Devolver usuarios
    // [x] Devolver roles
    
    UsuarioInterface model = new UsuarioModelo();
    List<Empleado> listaEmpleados =  model.obtenerEmpleados();
    
    RolInterface rolModelo = new RolModelo();
    List<Rol> listaRoles = rolModelo.obtenerRoles();
    
    // Para la vista: mantenimiento/usuarios estará disponible
    // listaEmpleados y listaRoles
    request.setAttribute("listaEmpleados", listaEmpleados);
    request.setAttribute("listaRoles", listaRoles);
    
    if (debugMode) {
      response.setContentType("application/json");
      request.setCharacterEncoding("UTF-8");
      
      Gson gson = new Gson();
      String json = gson.toJson(listaEmpleados);
      PrintWriter out = response.getWriter();
      out.write(json);
      return;
    }
    
    request.getRequestDispatcher("/mantenimientos/usuarios.jsp").forward(request, response);
    
  }
  
  protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // [x] Obtener campos
    // [x] Usar Modelo
    
    Empleado usuario = new Empleado();
    
    usuario.setNombre(request.getParameter("txtNombre"));
    usuario.setTipoDocumento(request.getParameter("txtTipoDocumento"));
    usuario.setDocumento(request.getParameter("txtDocumento"));
    usuario.setIdRole(Integer.parseInt(request.getParameter("txtRolId")));
    usuario.setUsuario(request.getParameter("txtUsuario"));
    String plainPsw = request.getParameter("txtPassword");
    
    UsuarioInterface model = new UsuarioModelo();
    int value = model.crearUsuario(usuario, plainPsw);
    
    if (value < 1) {
      // falló - se agrega mensaje antes de volver
      request.setAttribute("mensaje", "Falló el regitro");
      
    }
    
    if (debugMode) {
      response.setContentType("application/json");
      request.setCharacterEncoding("UTF-8");
      
      Gson gson = new Gson();
      String json = gson.toJson("Registro exitoso");
      PrintWriter out = response.getWriter();
      out.write(json);
      return;
    }
    
    // registro exitoso
    loadConfigUsuarios(request, response);
  }
  
  protected void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Empleado usuario = new Empleado();
    
    usuario.setIdPersona(Integer.parseInt(request.getParameter("idPersona")));
    usuario.setNombre(request.getParameter("txtNombre"));
    usuario.setTipoDocumento(request.getParameter("txtTipoDocumento"));
    usuario.setDocumento(request.getParameter("txtDocumento"));
    usuario.setIdRole(Integer.parseInt(request.getParameter("txtRolId")));
    usuario.setUsuario(request.getParameter("txtUsuario"));
    String plainPswParameter = request.getParameter("txtPassword");
    String plainPsw = null;
    if (plainPswParameter != null && !plainPswParameter.equals("")) {
      plainPsw = plainPswParameter;
    }
    
    UsuarioInterface model = new UsuarioModelo();
    int value = model.actualizarUsuario(usuario, plainPsw);
    
    if (value < 1) {
      // falló - se agrega mensaje antes de volver
      request.setAttribute("mensaje", "Falló la actualización");
    }
    
//    if (debugMode) {
//      response.setContentType("application/json");
//      request.setCharacterEncoding("UTF-8");
//      
//      Gson gson = new Gson();
//      String json = gson.toJson("Actualización exitosa");
//      PrintWriter out = response.getWriter();
//      out.write(json);
//      return;
//    }
    
    // registro exitoso
    loadConfigUsuarios(request, response);
  }

}
