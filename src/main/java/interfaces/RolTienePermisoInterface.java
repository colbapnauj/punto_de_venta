package interfaces;

import java.util.List;

import entidades.Permiso;
import entidades.RolTienePermiso;

public interface RolTienePermisoInterface {

	public void actualizarPermisosDeRol(int idRol, List<Integer> nuevosPermisos);
	
	public List<Permiso> obtenerPermisosSegunRol(String idRol);
	
	public List<RolTienePermiso> obtenerRolesYPermisos(); 
}
