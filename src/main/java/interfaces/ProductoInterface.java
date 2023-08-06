package interfaces;

import java.util.List;
import entidades.Producto;

public interface ProductoInterface {

	public List<Producto> obtenerProductos();
	
	public int crearProducto(Producto producto);
	
	public int editarProducto(Producto producto);
	
	public Producto obtenerProducto(int idProducto);
	
	public int eliminarProducto(int idProducto);
}
