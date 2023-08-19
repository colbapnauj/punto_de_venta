package entidades;

public class Mesa {
	private int idMesa;
	private String descripcion;
	private int cantidadPedidos;
	
	public Mesa() {
	}
	
	public Mesa(int idMesa, String descripcion, int cantidadPedidos) {
		super();
		this.idMesa = idMesa;
		this.descripcion = descripcion;
		this.cantidadPedidos = cantidadPedidos;
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
	public int getCantidadPedidos() {
		return cantidadPedidos;
	}
	public void setCantidadPedidos(int cantidadPedidos) {
		this.cantidadPedidos = cantidadPedidos;
	}
	
}
