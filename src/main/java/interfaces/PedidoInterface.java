package interfaces;

import java.util.List;
import entidades.ProductoCantidad;
import entidades.Pedido;
import entidades.Mesa;

public interface PedidoInterface {

	public List<Pedido> obtenerPedidos();
	
	public List<Pedido> obtenerPedidosPorUsuario(int idUsuario);
	
	public Pedido obtenerPedidoConDetalle(int idPedido, int idEmpleado);
	
	public boolean crearPedido(int idUsuario, int idMesa, List<ProductoCantidad> productos);
	
	public List<Mesa> obtenerMesas();
	
}
