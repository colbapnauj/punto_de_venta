package entidades;

public class Empleado extends Persona{
	private int idRole;
	private String usuario;
	private String rol;

	public Empleado(int idPersona, String nombre, String tipoDocumento, String doucmento, int idRole,
			String usuario, String password, String rol) {
		super(idPersona, nombre, tipoDocumento, doucmento);
		this.idRole = idRole;
		this.usuario = usuario;
		this.rol = rol;
	}
	
	public Empleado() {
		
	}
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
