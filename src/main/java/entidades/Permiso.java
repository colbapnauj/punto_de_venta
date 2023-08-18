package entidades;

public class Permiso {
	private int idPermiso;
	private String nombre;
	
	public Permiso() {
		super();
	}
	
	public Permiso(int idPermiso, String nombre) {
		super();
		this.idPermiso = idPermiso;
		this.nombre = nombre;
	}
	
	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	
}
