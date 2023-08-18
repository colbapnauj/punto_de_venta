package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import db.MysqlConexion;
import entidades.Permiso;
import entidades.RolTienePermiso;
import interfaces.RolTienePermisoInterface;

public class RolTienePermisoModelo implements RolTienePermisoInterface {

	@Override
	public void actualizarPermisosDeRol(int idRol, List<Integer> nuevosPermisos) {
		Connection cn = null;
		PreparedStatement psmInsert = null;
		PreparedStatement psmDelete = null;
		PreparedStatement psmExist = null;
		
		try {
			cn = MysqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String sqlInsert = "INSERT INTO roles_permisos (id_role, id_permiso) VALUES (?, ?)";
			String sqlExist = "SELECT COUNT(*) FROM roles_permisos WHERE id_role = ? AND id_permiso = ?";
			psmInsert = cn.prepareStatement(sqlInsert);
			psmExist = cn.prepareStatement(sqlExist);
			
			
			for (Integer idPermiso : nuevosPermisos) {
				psmExist.setInt(1, idRol);;
				psmExist.setInt(2,  idPermiso);
				ResultSet rs = psmExist.executeQuery();
				rs.next();
				int count = rs.getInt(1);
				if (count == 0) {
					psmInsert.setInt(1, idRol);
					psmInsert.setInt(2, idPermiso);
					psmInsert.executeUpdate();					
				}
				
			}
			
			// Eliminar permisos que no estén en la nueva lista
			String sqlDelete = "DELETE FROM roles_permisos WHERE id_role = ? AND id_permiso NOT IN (" + obtenerCadenaIds(nuevosPermisos.size()) + ")";
			psmDelete = cn.prepareStatement(sqlDelete);
			psmDelete.setInt(1, idRol);
			
			for (int i = 0; i < nuevosPermisos.size(); i++) {
				psmDelete.setInt(i + 2, nuevosPermisos.get(i));
			}
			psmDelete.executeUpdate();
			
			
			cn.commit();
			
		} catch (Exception e) {
			if (cn != null) {
				try {
					cn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			// Cerrar recursos
		}
	}
	
	private String obtenerCadenaIds(int cantidad) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cantidad; i++) {
			sb.append("?");
			if (i < cantidad - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	@Override
	public List<Permiso> obtenerPermisosSegunRol(String idRol) {
		List<Permiso> listaPermisos= new ArrayList<Permiso>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT rp.id_role, p.id_permiso, p.nombre FROM roles_permisos as rp \r\n"
					+ "INNER JOIN permisos AS p ON rp.id_permiso = p.id_permiso\r\n"
					+ "WHERE rp.id_role = ?;";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, idRol);
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
	public List<RolTienePermiso> obtenerRolesYPermisos() {
		
		List<RolTienePermiso> rolesYPermisos = new ArrayList<RolTienePermiso>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT r.id_role, r.nombre AS nombre_rol, p.id_permiso, p.nombre AS nombre_permiso FROM roles_permisos as rp \r\n"
					+ "INNER JOIN roles AS r ON rp.id_role = r.id_role\r\n"
					+ "INNER JOIN permisos AS p ON rp.id_permiso = p.id_permiso\r\n"
					+ "ORDER BY r.id_role;";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next() ) {
				RolTienePermiso roleTienePermiso = new RolTienePermiso();
				roleTienePermiso.setIdRol(rs.getInt("id_role"));
				roleTienePermiso.setNombreRol(rs.getString("nombre_rol"));
				
				roleTienePermiso.setIdPermiso(rs.getInt("id_permiso"));
				roleTienePermiso.setNombrePermiso(rs.getString("nombre_permiso"));
				
				rolesYPermisos.add(roleTienePermiso);
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
		return rolesYPermisos;
	}

}
