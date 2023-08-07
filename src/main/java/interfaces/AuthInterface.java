package interfaces;

import entidades.Empleado;

public interface AuthInterface {
	
	public Empleado verificarInicioSesion(String usuario, String passowrd);
}
