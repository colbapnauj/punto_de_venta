package entidades;

public class Persona {
	
	private int idPersona;
	private String nombre;
	private String tipoDocumento; // Dni, Pasaporte, CE
	private String documento;
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String doucmento) {
		this.documento = doucmento;
	}
	public Persona(int idPersona, String nombre, String tipoDocumento, String documento) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}
	public Persona() {
		
	}

}
