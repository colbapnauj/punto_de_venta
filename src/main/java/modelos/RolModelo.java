package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Rol;
import interfaces.RolInterface;

public class RolModelo implements RolInterface {

	@Override
	public List<Rol> obtenerRoles() {
		List<Rol> listaRoles = new ArrayList<Rol>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM roles";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next() ) {
				Rol mesa = new Rol();
				mesa.setIdRol(rs.getInt("id_role"));
				mesa.setNombre(rs.getString("nombre"));
				listaRoles.add(mesa);
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
		
		return listaRoles;
	}

	@Override
	public Rol obtenerRol(String idRol) {
		Rol rol = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM roles WHERE id_role=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, idRol);
			rs = psm.executeQuery();
			
			if (rs.next() ) {
				rol = new Rol();
				rol.setIdRol(rs.getInt("id_role"));
				rol.setNombre(rs.getString("nombre"));
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
		return rol;
	}

	@Override
	public int crearRol(Rol rol) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			
			String sql = "INSERT INTO roles (nombre) VALUES(?)";
			
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, rol.getNombre());
			
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
	public int editarRol(Rol rol) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "UPDATE roles SET nombre=? WHERE id_role=?";
			
			psm = cn.prepareStatement(sql);
			psm.setString(1, rol.getNombre());
			psm.setInt(2, rol.getIdRol());
			
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
	public int eliminarRol(String idRol) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "DELETE FROM roles WHERE id_role=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1,  idRol);
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
