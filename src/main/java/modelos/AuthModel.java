package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.MysqlConexion;
import entidades.Empleado;
import interfaces.AuthInterface;
import utils.BCrypt;

public class AuthModel implements AuthInterface{

	@Override
	public Empleado verificarInicioSesion(String usuario, String password) {
		Empleado empleado = null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String mysql = "SELECT em.usuario, pe.nombre, ro.nombre, em.password\r\n"
					+ "FROM empleado AS em\r\n"
					+ "INNER JOIN persona AS pe ON em.id_persona = pe.id_persona\r\n"
					+ "INNER JOIN roles AS ro ON em.id_role = ro.id_role\r\n"
					// + "WHERE em.usuario = ? AND em.password = ? ";
					+ "WHERE em.usuario = ? ";
			
			
			
			psmt = cn.prepareStatement(mysql);
			psmt.setString(1, usuario);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			
			
			
			String hashedPassword = hashPassword(password);
			System.out.println(hashedPassword);
			// boolean isValid = BCrypt.checkpw(password, hashedPassword);
			// System.out.println(isValid);
			boolean isValid = false;
			if (rs.next() ) {
				String hashedPasswordDb = rs.getString("password");
				isValid = BCrypt.checkpw(password, hashedPasswordDb);
			}
			
			if (isValid) {
				empleado = new Empleado();
				empleado.setNombre(rs.getString("nombre"));
				empleado.setDocumento(mysql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return empleado;
		
	}
	
	private String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt();
        return  BCrypt.hashpw(plainPassword, salt);
    }
	

}
