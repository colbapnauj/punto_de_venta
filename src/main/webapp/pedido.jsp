<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Producto" %>
<%@ page import="modelos.ProductoModelo" %>
<%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">
    <% 
        ProductoModelo productoModelo = new ProductoModelo();
        List<Producto> listaProductos = productoModelo.obtenerProductos();

        for (Producto producto : listaProductos) {
            %>
            <div class="col-md-4">
                <div class="card mb-4">
                    <img src="<%= producto.getFoto() %>" class="card-img-top" alt="<%= producto.getDescripcion() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= producto.getDescripcion() %></h5>
                        <p class="card-text">Precio: $<%= producto.getPrecio() %></p>
                    </div>
                </div>
            </div>
            <% 
        }
    %>
</div>
</body>
</html>