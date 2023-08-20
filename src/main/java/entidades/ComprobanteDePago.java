package entidades;

public class ComprobanteDePago {
	
	public static final double PORCENTAJE_IGV = 0.18;
	
	private int idComprobante;
	private int idMesa;
	private int idPedido;
	private double total;
	
	private int idCliente;
	private String tipoComprobante;
	private String fecha;
	private double dbIgv;
	private String mesaDescripcion;
	
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
	public int getIdCliente() {
    return idCliente;
  }
  public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
  }
  public String getTipoComprobante() {
    return tipoComprobante;
  }
  public void setTipoComprobante(String tipoComprobante) {
    this.tipoComprobante = tipoComprobante;
  }
  public String getFecha() {
    return fecha;
  }
  public void setFecha(String fecha) {
    this.fecha = fecha;
  }
  public double getIGV() {
		return this.total - this.getTotal() / (1 + PORCENTAJE_IGV);
	}
  public double getDbIgv() {
    return this.dbIgv;
  }
  public void setDbIgv(double dbIgv) {
    this.dbIgv = dbIgv;
  }
  public String getMesaDescripcion() {
    return this.mesaDescripcion;
  }
  public void setMesaDescripcion(String mesaDescripcion) {
    this.mesaDescripcion = mesaDescripcion;
  }

}
