package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Mesa;
import interfaces.MesaInterface;

public class MesaModelo implements MesaInterface {

	@Override
	public List<Mesa> obtenerMesas() {
		List<Mesa> listaMesas = new ArrayList<Mesa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM mesa";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next() ) {
				Mesa mesa = new Mesa();
				mesa.setIdMesa(rs.getInt("id_mesa"));
				mesa.setDescripcion(rs.getString("descripcion"));
				listaMesas.add(mesa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaMesas;
	}

	@Override
	public Mesa obtenerMesa(String idMesa) {
		Mesa mesa = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM mesa WHERE id_mesa=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, idMesa);
			rs = psm.executeQuery();
			
			if (rs.next() ) {
				mesa = new Mesa();
				mesa.setIdMesa(rs.getInt("id_mesa"));
				mesa.setDescripcion(rs.getString("descripcion"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null) rs.close();
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return mesa;
	}

	@Override
	public int crearMesa(Mesa mesa) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			
			String sql = "INSERT INTO mesa (descripcion) VALUES(?)";
			
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, mesa.getDescripcion());
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public int editarMesa(Mesa mesa) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "UPDATE mesa SET descripcion=? WHERE id_mesa=?";
			
			psm = cn.prepareStatement(sql);
			psm.setString(1, mesa.getDescripcion());
			psm.setInt(2, mesa.getIdMesa());
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public int eliminarMesa(String idMesa) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "DELETE FROM mesa WHERE id_mesa=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1,  idMesa);
			value = psm.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
}
