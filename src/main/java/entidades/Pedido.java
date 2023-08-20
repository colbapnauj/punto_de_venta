package entidades;

import java.util.List;

public class Pedido {
  // FIXME: Debería ser createdBy
	private int idPedido;
	private int idEmpleado;
	private int idMesa;
	private String usuario;
	private String createdAt;
	private String mesa;
	private List<PedidoProducto> detalle;

	public Pedido() {
    super();
  }

  public Pedido(int idPedido, int idEmpleado, int idMesa, String usuario, String createdAt, String mesa,
      List<PedidoProducto> detalle) {
    super();
    this.idPedido = idPedido;
    this.idEmpleado = idEmpleado;
    this.idMesa = idMesa;
    this.usuario = usuario;
    this.createdAt = createdAt;
    this.mesa = mesa;
    this.detalle = detalle;
  }

  public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public List<PedidoProducto> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<PedidoProducto> detalle) {
		this.detalle = detalle;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
	
	
}
