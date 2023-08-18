package interfaces;

import java.util.List;

import entidades.Rol;

public interface RolInterface {
	
	public List<Rol> obtenerRoles();
	
	public Rol obtenerRol(String idRol);
	
	public int crearRol(Rol rol);
	
	public int editarRol(Rol rol);
	
	public int eliminarRol(String idRol);

}
