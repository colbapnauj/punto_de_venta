package entidades;

public class ComprobanteDePago {
	
	public static final double PORCENTAJE_IGV = 0.18;
	
	private int idComprobante;
	private int idMesa;
	private int idPedido;
	private double total;
	
	public ComprobanteDePago() {
		super();
	}
	
	public ComprobanteDePago(int idComprobante, int idMesa, int idPedido, double total) {
		super();
		this.idComprobante = idComprobante;
		this.idMesa = idMesa;
		this.idPedido = idPedido;
		this.total = total;
	}
	
	public int getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getIGV() {
		return this.getTotal() / (1 + PORCENTAJE_IGV);
	}
	

}
