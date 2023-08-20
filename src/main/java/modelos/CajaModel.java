package modelos;

import interfaces.CajaInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MysqlConexion;
import entidades.ComprobanteDePago;

public class CajaModel implements CajaInterface {
	
	private static final String TIPO_COMPROBANTE= "Boleta"; 

	@Override
	public boolean crearComprobanteDePago(ComprobanteDePago comprobante) {
		
		Connection cn = null;
		PreparedStatement psInsert = null;
		PreparedStatement psUpdate = null;
		
		try {
			cn = MysqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String insertQuery = "INSERT INTO comprobante_pago\r\n"
					+ "(id_pedido, tipo_comprobante, igv, total) \r\n"
					+ "VALUES \r\n"
					+ "(?, ?, ?, ?);";
			psInsert = cn.prepareStatement(insertQuery);
			psInsert.setInt(1, comprobante.getIdPedido());
			psInsert.setString(2, TIPO_COMPROBANTE);
			psInsert.setDouble(3, comprobante.getIGV());
			psInsert.setDouble(4, comprobante.getTotal());
			psInsert.executeUpdate();
			
			String updateQuery = "UPDATE pedido\r\n"
					+ "SET estado = 0\r\n"
					+ "WHERE id_pedido = ?;";
			psUpdate = cn.prepareStatement(updateQuery);
			psUpdate.setInt(1, comprobante.getIdPedido());
			psUpdate.executeUpdate();
			
			cn.commit();
			
			return true;
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
				if (cn != null) cn.close();
				if (psInsert != null) psInsert.close();				
				if (psUpdate != null) psUpdate.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
	}

  @Override
  public double obtenerTotalDePedido(int idPedido) {
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double total = 0;
    
    try {
      cn = MysqlConexion.getConexion();
      String query = "SELECT p.id_pedido, p.estado, SUM(prod.precio * dp.cantidad) AS total FROM pedido AS p\r\n"
          + "JOIN detalle_pedido AS dp ON dp.id_pedido = p.id_pedido\r\n"
          + "JOIN producto AS prod ON prod.id_producto = dp.id_producto\r\n"
          + "WHERE p.id_pedido = ?\r\n"
          + "GROUP BY p.id_pedido;";
      ps = cn.prepareStatement(query);
      ps.setInt(1, idPedido);
      rs = ps.executeQuery();
      
      if (rs.next()) {
        total = rs.getDouble("total");
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
    return total;
  }

}
