package modelos;

import interfaces.FacturacionInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.MysqlConexion;
import entidades.ComprobanteDePago;

public class FacturacionModel implements FacturacionInterface {
	
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

}
