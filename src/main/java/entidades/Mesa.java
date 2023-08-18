package entidades;

public class Mesa {
	private int idMesa;
	private String descripcion;
	
	public Mesa() {
	}
	
	public Mesa(int idMesa, String descripcion) {
		super();
		this.idMesa = idMesa;
		this.descripcion = descripcion;
	}
	
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
