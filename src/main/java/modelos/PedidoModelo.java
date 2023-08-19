package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import db.MysqlConexion;
import interfaces.PedidoInterface;
import entidades.Pedido;
import entidades.Producto;
import entidades.ProductoCantidad;
import entidades.PedidoProducto;
import entidades.Mesa;


public class PedidoModelo implements PedidoInterface {
	
	@Override
	public List<Pedido> obtenerPedidos() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		try {
			cn = MysqlConexion.getConexion();
			String consulta = "SELECT * from pedido";
			ps = cn.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("id_pedido"));
				pedido.setIdEmpleado(rs.getInt("id_empleado"));
				pedido.setIdMesa(rs.getInt("id_mesa"));
				
				listaPedidos.add(pedido);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaPedidos;
	}
	
	@Override
	public Pedido obtenerPedidoConDetalle(int idPedido, int idEmpleado) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pedido pedido = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT\r\n"
					+ "	P.id_pedido,\r\n"
					+ "	PP.id_producto,\r\n"
					+ "	P.id_empleado,\r\n"
					+ "	M.descripcion AS mesa,\r\n"
					+ "	Prod.foto,\r\n"
					+ "	Prod.descripcion,\r\n"
					+ "	PP.cantidad,\r\n"
					+ " Prod.precio\r\n"
					+ "FROM pedido AS P\r\n"
					+ "INNER JOIN detalle_pedido AS PP ON P.id_pedido = PP.id_pedido\r\n"
					+ "INNER JOIN producto AS Prod ON Prod.id_producto = PP.id_producto\r\n"
					+ "INNER JOIN mesa AS M ON M.id_mesa = P.id_mesa\r\n"
					+ "WHERE	P.id_empleado = ? AND P.id_pedido = ?\r\n"
					+ "ORDER BY P.id_pedido ASC;";
			
			ps = cn.prepareStatement(query);
			ps.setInt(1, idEmpleado);
			ps.setInt(2, idPedido);
			rs = ps.executeQuery();
			
			List<PedidoProducto> detalle = new ArrayList<PedidoProducto>();
			
			while (rs.next()) {
				
				if (pedido == null) {
					pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("id_pedido"));
					pedido.setMesa(rs.getString("mesa"));
					pedido.setIdEmpleado(rs.getInt("id_empleado"));
				}

				PedidoProducto pedidoProducto = new PedidoProducto();
				
				Producto producto = new Producto();
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFoto(rs.getString("foto"));
				
				pedidoProducto.setCantidad(rs.getInt("cantidad"));
				pedidoProducto.setProducto(producto);
				
				detalle.add(pedidoProducto);
			}
			
			pedido.setDetalle(detalle);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return pedido;
	}
	
	@Override
	public List<Pedido> obtenerPedidosPorUsuario(int idUsuario) {
	
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT\r\n"
					+ "	P.id_pedido,\r\n"
					+ "	PP.id_producto,\r\n"
					+ "	P.id_empleado,\r\n"
					+ "	M.descripcion AS mesa,\r\n"
					+ "	Prod.foto,\r\n"
					+ "	Prod.descripcion,\r\n"
					+ "	PP.cantidad,\r\n"
					+ " Prod.precio\r\n"
					+ "FROM pedido AS P\r\n"
					+ "INNER JOIN detalle_pedido AS PP ON P.id_pedido = PP.id_pedido\r\n"
					+ "INNER JOIN producto AS Prod ON Prod.id_producto = PP.id_producto\r\n"
					+ "INNER JOIN mesa AS M ON M.id_mesa = P.id_mesa\r\n"
					+ "WHERE	P.id_empleado = ?\r\n"
					+ "ORDER BY P.id_pedido ASC;";
			
			ps = cn.prepareStatement(query);
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("id_pedido"));
				pedido.setMesa(rs.getString("mesa"));
				pedido.setIdEmpleado(rs.getInt("id_empleado"));
					
				List<PedidoProducto> detalle = new ArrayList<PedidoProducto>();
				
				PedidoProducto pedidoProducto = new PedidoProducto();
						
				Producto producto = new Producto();
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFoto(rs.getString("foto"));
						
				pedidoProducto.setCantidad(rs.getInt("cantidad"));
				pedidoProducto.setProducto(producto);
						
				detalle.add(pedidoProducto);

				pedido.setDetalle(detalle);
				
				listaPedidos.add(pedido);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaPedidos;
	}
	
	@Override
	public boolean crearPedido(int idUsuario, int idMesa, List<ProductoCantidad> productos) {
		
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = MysqlConexion.getConexion();
			cn.setAutoCommit(false);
			
			String query = "INSERT INTO pedido (id_empleado, id_mesa) VALUES (?, ?)";
			ps = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idMesa);
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				int idPedido = generatedKeys.getInt(1);
				
				String detalleInsertQuery = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad)\r\n"
						+ "VALUES\r\n"
						+ "(?, ?, ?)";
				
				PreparedStatement psDetalle = cn.prepareStatement(detalleInsertQuery);
				for (ProductoCantidad item : productos) {
					psDetalle.setInt(1, idPedido);
					psDetalle.setInt(2, item.getIdProducto());
					psDetalle.setInt(3, item.getCantidad());
					psDetalle.executeUpdate();
				}
			}
			
			cn.commit();
			return true;
			
		} catch (Exception e) {
			if (cn != null) {
				try {
					cn.rollback();					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (ps != null) ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return false;
	}
	
	@Override
	public List<Mesa> obtenerMesas() {
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Mesa> listaMesas = new ArrayList<Mesa>();
		
		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT m.id_mesa, m.descripcion, COUNT(p.id_pedido) AS cantidad_pedidos FROM mesa AS m\r\n"
					+ "LEFT OUTER JOIN pedido AS p ON p.id_mesa = m.id_mesa\r\n"
					+ "GROUP BY m.id_mesa";
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Mesa mesa = new Mesa();
				mesa.setIdMesa(rs.getInt("id_mesa"));
				mesa.setDescripcion(rs.getString("descripcion"));
				mesa.setCantidadPedidos(rs.getInt("cantidad_pedidos"));
				listaMesas.add(mesa);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		return listaMesas;
		
	}
	
}
