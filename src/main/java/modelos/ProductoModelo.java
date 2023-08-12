package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Producto;
import interfaces.ProductoInterface;

public class ProductoModelo implements ProductoInterface{

	@Override
	public List<Producto> obtenerProductos() {
		List<Producto> listaProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "SELECT * FROM producto";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next() ) {
				
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("id_producto"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFoto(rs.getString("foto"));
				
				listaProductos.add(producto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaProductos;
	}

	@Override
	public int crearProducto(Producto producto) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			// TODO Load image
			
			String sql = "INSERT INTO producto VALUES(null, ?, ?, null)";
			
			// si se envió una foto
			boolean hasPhotoRawName = producto.getFotoRawName() != null; 
			if (hasPhotoRawName) {
				sql = "INSERT INTO producto VALUES(null, ?, ?, ?)";
			}
			
			psm = cn.prepareStatement(sql);
			
			psm.setString(1, producto.getDescripcion());
			psm.setDouble(2, producto.getPrecio());
			
			// si se envió una foto
			if (hasPhotoRawName) {
				psm.setString(3, producto.getFotoRawName());
			}
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public Producto obtenerProducto(int idProducto) {
		Producto producto = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "SELECT prod.* FROM producto AS prod WHERE id_producto=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, idProducto);
			rs = psm.executeQuery();
			
			if (rs.next() ) {
				producto = new Producto();
				producto.setIdProducto(rs.getInt("id_producto"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFoto(rs.getString("foto"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null) rs.close();
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return producto;
	}

	@Override
	public int eliminarProducto(int idProducto) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "DELETE FROM producto WHERE id_producto=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1,  idProducto);
			value = psm.executeUpdate();
			
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public int editarProducto(Producto producto) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "UPDATE producto SET descripcion=?, precio=? WHERE id_producto=?";
			
			// si se envió una foto
			boolean hasPhotoRawName = producto.getFotoRawName() != null; 
			if (hasPhotoRawName) {
				sql = "UPDATE producto SET descripcion=?, precio=?, foto=? WHERE id_producto=?";
			}
			
			psm = cn.prepareStatement(sql);
			psm.setString(1, producto.getDescripcion());
			psm.setDouble(2, producto.getPrecio());
			
			if (hasPhotoRawName) {
				psm.setString(3, producto.getFotoRawName());
				psm.setInt(4, producto.getIdProducto());
			} else {
				psm.setInt(3, producto.getIdProducto());
			}
			
			
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

}
