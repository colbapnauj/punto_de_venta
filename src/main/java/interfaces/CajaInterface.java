package interfaces;

import entidades.ComprobanteDePago;

public interface CajaInterface {

	public boolean crearComprobanteDePago(ComprobanteDePago comprobante);
	
	public double obtenerTotalDePedido(int idPedido);
}
