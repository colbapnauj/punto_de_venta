package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import db.MysqlConexion;
import entidades.ComprobanteDePago;
import interfaces.ReporteInterface;

public class ReporteModel implements ReporteInterface {

  @Override
  public List<ComprobanteDePago> obtenerComprobantes(String fechaInicio, String fechaFin) {
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<ComprobanteDePago> listaComprobantes = new ArrayList<ComprobanteDePago>();
    
    try {
      
      cn = MysqlConexion.getConexion();
      // dateFormat = 'yyyy-mm-dd';
      String query = "SELECT \r\n"
          + "  cp.id_comprobante,\r\n"
          + "  m.id_mesa,\r\n"
          + "  cp.id_pedido,\r\n"
          + "  cp.id_cliente,\r\n"
          + "  cp.total,\r\n"
          + "  cp.igv,\r\n"
          + "  cp.tipo_comprobante,\r\n"
          + "  cp.fecha,\r\n"
          + "  m.descripcion AS mesa_descripcion\r\n"
          + "  FROM comprobante_pago AS cp\r\n"
          + "JOIN pedido AS p ON p.id_pedido = cp.id_pedido\r\n"
          + "JOIN mesa AS m ON m.id_mesa = p.id_mesa\r\n"
          + "WHERE \r\n"
          + "DATE_FORMAT(cp.fecha,'%Y-%m-%d') >=  ? AND\r\n"
          + "DATE_FORMAT(cp.fecha,'%Y-%m-%d') <=  ?;";
      ps = cn.prepareStatement(query);
      ps.setString(1, fechaInicio);
      ps.setString(2, fechaFin);
      rs = ps.executeQuery();
      
      while (rs.next()) {
        ComprobanteDePago comprobante = new ComprobanteDePago();
        comprobante.setIdComprobante(rs.getInt("id_comprobante"));
        comprobante.setIdPedido(rs.getInt("id_pedido"));
        comprobante.setIdCliente(rs.getInt("id_cliente"));
        comprobante.setTipoComprobante(rs.getString("tipo_comprobante"));
        comprobante.setFecha("fecha");
        comprobante.setTotal(rs.getDouble("total"));
        comprobante.setDbIgv(rs.getDouble("igv"));
        comprobante.setMesaDescripcion(rs.getString("mesa_descripcion"));
        
        listaComprobantes.add(comprobante);
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
    
    return listaComprobantes;
  }
}
