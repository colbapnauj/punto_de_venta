package interfaces;

import java.util.List;

import entidades.Permiso;

public interface PermisoInterface {
	public List<Permiso> obtenerPermisos();
	
	public Permiso obtenerPermiso(String idPermiso);
	
	public int crearPermiso(Permiso permiso);
	
	public int editarPermiso(Permiso permiso);
	
	public int eliminarPermiso(String idPermiso);
}
