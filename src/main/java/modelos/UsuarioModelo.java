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
          + "  u.id_role\r\n"
          + "FROM empleado AS u\r\n"
          + "JOIN persona AS per ON per.id_persona = u.id_persona;";
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
      
      psPersonaInsert = cn.prepareStatement(insertPersonaQuery);
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
        // TODO process plainPsq
        psInsertUser.setString(4, plainPsw);
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
  
  

}
