package entidades;

public class PedidoProducto {
	private int idPedido;
	private int cantidad;
	private Producto producto;
	public PedidoProducto(int idPedido, Producto producto, int cantidad) {
		super();
		this.idPedido = idPedido;
		this.producto= producto;
		this.cantidad = cantidad;
	}
	public PedidoProducto() {
		super();
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
