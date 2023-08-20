package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import db.MysqlConexion;
import entidades.Empleado;
import interfaces.UsuarioInterface;
import utils.Utils;

public class UsuarioModelo implements UsuarioInterface {

  @Override
  public List<Empleado> obtenerEmpleados() {
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    List<Empleado> listaEmpleados = new ArrayList<Empleado>();
    
    try {
      cn = MysqlConexion.getConexion();
      String query = "SELECT \r\n"
          + "  per.id_persona,\r\n"
          + "  per.nombre, \r\n"
          + "  per.tipo_documento, \r\n"
          + "  per.documento, \r\n"
          + "  u.usuario, \r\n"
          + "  u.id_role,\r\n"
          + "  r.nombre AS role_name\r\n"
          + "FROM empleado AS u\r\n"
          + "JOIN persona AS per ON per.id_persona = u.id_persona\r\n"
          + "JOIN roles AS r ON r.id_role = u.id_role;";
      ps = cn.prepareStatement(query);
      rs = ps.executeQuery();
      
      while (rs.next()) {
        Empleado usuario = new Empleado();
        usuario.setIdPersona(rs.getInt("id_persona"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setTipoDocumento(rs.getString("tipo_documento"));
        usuario.setDocumento(rs.getString("documento"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setIdRole(rs.getInt("id_role"));
        usuario.setRol(rs.getString("role_name"));
        
        listaEmpleados.add(usuario);
      }

    } catch (Exception e) {
      e.printStackTrace();
      
    } finally {
      try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (cn != null) cn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return listaEmpleados;
  }

  @Override
  public int crearUsuario(Empleado usuario, String plainPsw) {
    Connection cn = null;
    PreparedStatement psPersonaInsert = null;
    int value = 0;
    
    try {
      cn = MysqlConexion.getConexion();
      cn.setAutoCommit(false);
      
      String insertPersonaQuery = "INSERT INTO persona\r\n"
          + "(nombre, tipo_documento, documento)\r\n"
          + "VALUES\r\n"
          + "(?, ?, ?);\r\n";
      
      psPersonaInsert = cn.prepareStatement(insertPersonaQuery, PreparedStatement.RETURN_GENERATED_KEYS);
      psPersonaInsert.setString(1,  usuario.getNombre());
      psPersonaInsert.setString(2,  usuario.getTipoDocumento());
      psPersonaInsert.setString(3,  usuario.getDocumento());
      psPersonaInsert.executeUpdate();
      
      ResultSet generatedKeys = psPersonaInsert.getGeneratedKeys();
      
      if (generatedKeys.next()) {
        int idPersona = generatedKeys.getInt(1);
        String insertUserQuery = "INSERT INTO empleado\r\n"
            + "(id_persona, usuario, id_role, password)\r\n"
            + "VALUES\r\n"
            + "(?, ?, ?, ?);";
        PreparedStatement psInsertUser = cn.prepareStatement(insertUserQuery);
        psInsertUser.setInt(1, idPersona);
        psInsertUser.setString(2, usuario.getUsuario());
        psInsertUser.setInt(3, usuario.getIdRole());
         
        String hashedPassword = Utils.hashPassword(plainPsw);
        psInsertUser.setString(4, hashedPassword);
        value = psInsertUser.executeUpdate();
      }
      
      cn.commit();
      return value;
      
    } catch (Exception e) {
      e.printStackTrace();
      if (cn != null) {
        try {
          cn.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    } finally {
      try {
        if (psPersonaInsert != null) psPersonaInsert.close();
        if (cn != null) cn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return value;
  }

  @Override
  public int actualizarUsuario(Empleado usuario, String plainPsw) {
    Connection cn = null;
    PreparedStatement psUserUpdate = null;
    PreparedStatement psPersonaUpdate = null;
    int value = 0;
    
    try {
      cn = MysqlConexion.getConexion();
      cn.setAutoCommit(false);
      
      // Actualizar tabla empleado
      String updateUserQuery = "UPDATE empleado AS e\r\n"
          + "SET\r\n"
          + "e.id_role = ?,\r\n"
          + "e.usuario = ?\r\n"
          + (plainPsw != null ? "," : "\r\n")
          + (plainPsw != null ? "e.password = ?\r\n" : "\r\n")
          + "WHERE e.id_persona = ?";
      
      psUserUpdate = cn.prepareStatement(updateUserQuery);
      psUserUpdate.setInt(1, usuario.getIdRole());
      psUserUpdate.setString(2, usuario.getUsuario());
      if (plainPsw != null) {
        String hashedPassword = Utils.hashPassword(plainPsw);
        psUserUpdate.setString(3, hashedPassword);
        psUserUpdate.setInt(4, usuario.getIdPersona());
      } else {
        psUserUpdate.setInt(3, usuario.getIdPersona());
      }
      psUserUpdate.executeUpdate();
      
      // Actualizar tabla persona
      String updatePersonaQuery = "UPDATE persona AS p\r\n"
          + "SET \r\n"
          + "p.nombre = ?,\r\n"
          + "p.tipo_documento = ?,\r\n"
          + "p.documento = ?\r\n"
          + "WHERE p.id_persona = ?;";
      
      psPersonaUpdate = cn.prepareStatement(updatePersonaQuery);
      psPersonaUpdate.setString(1, usuario.getNombre());
      psPersonaUpdate.setString(2, usuario.getTipoDocumento());
      psPersonaUpdate.setString(3, usuario.getDocumento());
      psPersonaUpdate.setInt(4, usuario.getIdPersona());
      value = psPersonaUpdate.executeUpdate();
       
      cn.commit();
      
    } catch (Exception e) {
      e.printStackTrace();
      if (cn != null) {
        try {
          cn.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    } finally {
      try {
        if (psPersonaUpdate != null) psPersonaUpdate.close();
        if (psUserUpdate != null) psUserUpdate.close();
        if (cn != null) cn.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    return value;
  }
  
  

}