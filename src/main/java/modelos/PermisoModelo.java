package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Permiso;
import interfaces.PermisoInterface;

public class PermisoModelo implements PermisoInterface {

	@Override
	public List<Permiso> obtenerPermisos() {
		List<Permiso> listaPermisos= new ArrayList<Permiso>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM permisos";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next() ) {
				Permiso permiso = new Permiso();
				permiso.setIdPermiso(rs.getInt("id_permiso"));
				permiso.setNombre(rs.getString("nombre"));
				listaPermisos.add(permiso);
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
		
		return listaPermisos;
	}

	@Override
	public Permiso obtenerPermiso(String idPermiso) {
		Permiso permiso = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM permisos WHERE id_permiso=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, idPermiso);
			rs = psm.executeQuery();
			
			if (rs.next() ) {
				permiso = new Permiso();
				permiso.setIdPermiso(rs.getInt("id_permiso"));
				permiso.setNombre(rs.getString("nombre"));
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
		return permiso;
	}

	@Override
	public int crearPermiso(Permiso permiso) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			
			String sql = "INSERT INTO permisos (nombre) VALUES(?)";
			
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, permiso.getNombre());
			
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
	public int editarPermiso(Permiso permiso) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "UPDATE permisos SET nombre=? WHERE id_permiso=?";
			
			psm = cn.prepareStatement(sql);
			psm.setString(1, permiso.getNombre());
			psm.setInt(2, permiso.getIdPermiso());
			
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
	public int eliminarPermiso(String idPermiso) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "DELETE FROM permisos WHERE id_permiso=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1,  idPermiso);
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
