<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Producto"%>
<%@ page import="modelos.ProductoModelo"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos de la Mesa</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.producto-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.producto-item {
    text-align: center;
    margin: 10px;
    width: 100px;
    position: relative;
}

.producto-img {
    max-width: 100%;
}

.producto-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.7);
    z-index: -1;
    border-radius: 10px;
}

.producto-nombre {
    font-weight: bold;
    margin-top: 5px;
    font-style: italic;
    font-family: 'Arial', sans-serif;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: white;
    background-color: rgba(0, 0, 0, 0.7);
    padding: 5px 10px;
    border-radius: 5px;
    font-family: cursive, sans-serif;
}
</style>
</head>
<body style="background-image: url('img/fondo.jpg'); background-size: cover; background-repeat: repeat-y; width:100%; height: 100%; margin: 0; padding: 0;">
    <%@ include file="menu.jsp"%>
    <div class="container">
        <h1 class="text-center mt-3">Productos</h1>
        <div class="producto-container mt-3">
            <% 
            List<Producto> dataProductos = (List<Producto>) request.getAttribute("dataProductos");
            
            if (dataProductos != null && !dataProductos.isEmpty()) {
                for (Producto producto : dataProductos) { 
            %>
                <div class="producto-item" id="producto<%=producto.getIdProducto()%>">
                    <div class="producto-overlay"></div>
                    <img class="producto-img" src="<%=producto.getFoto()%>" alt="Producto <%=producto.getIdProducto()%>">
                    <div class="producto-nombre">
                        Producto: <%=producto.getDescripcion()%><br>
                        Descripción: <%=producto.getDescripcion()%><br>
                        Precio: <%=producto.getPrecio()%>
                        <br>
                        <a class="btn btn-primary" href="pedido.jsp?type=info&idProducto=<%=producto.getIdProducto()%>">Editar</a>
                        <a class="btn btn-danger" href="ProductoServlet?type=delete&idProducto=<%=producto.getIdProducto()%>">Eliminar</a>
                    </div>
                </div>
            <% 
                } 
            } else {
            %>
                <p>No hay productos disponibles.</p>
            <% 
            } 
            %>
        </div>
        <hr>
        <h2 class="text-center">Crear Nuevo Producto</h2>
        <form action="ProductoServlet?type=register" method="post">
            <div class="form-group">
                <label for="txtDescripcion">Descripción:</label>
                <input type="text" class="form-control" id="txtDescripcion" name="txtDescripcion" required>
            </div>
            <div class="form-group">
                <label for="txtPrecio">Precio:</label>
                <input type="number" step="0.01" class="form-control" id="txtPrecio" name="txtPrecio" required>
            </div>
            <!-- <div class="form-group">
                <label for="file">Imagen:</label>
                <input type="file" class="form-control-file" id="file" name="file">
            </div> -->
            <button type="submit" class="btn btn-success">Crear Producto</button>
        </form>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
