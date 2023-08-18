package entidades;

public class RolTienePermiso {
	private int idRol;
	private int idPermiso;
	private String nombreRol;
	private String nombrePermiso;
	
	public RolTienePermiso() {
		super();
	}
	public RolTienePermiso(int idRol, int idPermiso, String nombreRol, String nombrePermiso) {
		super();
		this.idRol = idRol;
		this.idPermiso = idPermiso;
		this.nombreRol = nombreRol;
		this.nombrePermiso = nombrePermiso;
	}
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public String getNombrePermiso() {
		return nombrePermiso;
	}
	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}	
}
