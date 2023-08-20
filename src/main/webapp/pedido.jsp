<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidades.Producto"%>
<%@ page import="modelos.ProductoModelo"%>
<%@ page import="java.util.List"%>

<%
String mesaParam = request.getParameter("mesa");

if (mesaParam != null && !mesaParam.isEmpty()) {
	int mesa = Integer.parseInt(mesaParam);

	ProductoModelo model = new ProductoModelo();
	List<Producto> dataProductos = model.obtenerProductos();

	request.setAttribute("dataProductos", dataProductos);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos de la Mesa</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.producto-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.producto-item {
	display: flex;
	align-items: center;
	margin: 10px;
	width: 100%;
	position: relative;
}

.producto-img {
	max-width: 200px;
	width: auto;
	height: auto;
	flex: 1;
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
<body
	style="background-image: url('img/fondo.jpg'); background-size: cover; background-repeat: repeat-y; width: 100%; height: 100%; margin: 0; padding: 0;">
	<%@ include file="menu.jsp"%>
	<div class="container">
		<h1 class="text-center mt-3">Mesa ${param.mesa}</h1>
		<h1 class="text-center mt-3">Productos</h1>
		<form action="PedidosServlete?type=crearPedido" method="post">
			<div class="row">
				<%
				List<Producto> dataProductos = (List<Producto>) request.getAttribute("dataProductos");
				if (dataProductos != null && !dataProductos.isEmpty()) {
					for (Producto producto : dataProductos) {
				%>
				<div class="col-md-6 mb-4">
					<div class="producto-item">
						<img class="producto-img" src="<%=producto.getFoto()%>"
							alt="Producto <%=producto.getIdProducto()%>">
						<div class="producto-overlay">
							<h5 class="card-title">
								Producto:
								<%=producto.getDescripcion()%></h5>
						</div>
						<div class="card-body">
							<br>
							<p class="card-text">
								Descripción:
								<%=producto.getDescripcion()%><br> <strong>
									Precio: <%=producto.getPrecio()%></strong>
							</p>
							<div class="form-group">
								<input type="number" class="form-control"
									id="cantidad<%=producto.getIdProducto()%>"
									name="cantidad<%=producto.getIdProducto()%>" value="1" min="1">
								<input type="hidden"
									name="productoId<%=producto.getIdProducto()%>"
									value="<%=producto.getIdProducto()%>">
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="seleccion<%=producto.getIdProducto()%>"
									name="seleccion<%=producto.getIdProducto()%>"
									value="<%=producto.getIdProducto()%>"> <label
									class="form-check-label"
									for="seleccion<%=producto.getIdProducto()%>">Seleccionar</label>
							</div>
						</div>
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

				<div class="form-group mt-3">
					<input type="hidden" name="idMesa" value="<%=mesaParam%>">
					<button type="submit" class="btn btn-success">Agregar al
						Pedido</button>
				</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

