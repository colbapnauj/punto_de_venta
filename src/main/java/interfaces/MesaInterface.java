package interfaces;

import java.util.List;

import entidades.Mesa;

public interface MesaInterface {
	
	public List<Mesa> obtenerMesas();
	
	public Mesa obtenerMesa(String idMesa);
	
	public int crearMesa(Mesa mesa);
	
	public int editarMesa(Mesa mesa);
	
	public int eliminarMesa(String idMesa);

}
