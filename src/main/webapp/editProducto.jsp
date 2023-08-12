<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Producto"%>
<%@ page import="modelos.ProductoModelo"%>
<%
    int idProducto = Integer.parseInt(request.getParameter("idProducto"));
    ProductoModelo productoModelo = new ProductoModelo();
    Producto producto = productoModelo.obtenerProducto(idProducto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Producto</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
/* Estilos para el fondo y la letra */
body {
    background-image: url('img/fondo.jpg');
    background-size: cover;
    background-repeat: repeat-y;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    color: white;
}

.container {
    margin-top: 50px;
}
</style>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container">
        <h1 class="text-center mt-3">Editar Producto</h1>
        <form action="ProductoServlet?type=register" method="post">
            <div class="form-group">
                <label for="txtDescripcion">Descripción:</label>
                <input type="text" class="form-control" id="txtDescripcion" name="txtDescripcion" value="<%=producto.getDescripcion()%>" required>
            </div>
            <div class="form-group">
                <label for="txtPrecio">Precio:</label>
                <input type="number" step="0.01" class="form-control" id="txtPrecio" name="txtPrecio" value="<%=producto.getPrecio()%>" required>
            </div>
            <!-- TODO: Agregar campo para cargar imagen -->
            <!-- <div class="form-group">
                <label for="file">Imagen:</label>
                <input type="file" class="form-control-file" id="file" name="file">
            </div> -->
            <input type="hidden" name="idProducto" value="<%=producto.getIdProducto()%>">
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
