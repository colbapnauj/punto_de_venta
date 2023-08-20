<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="entidades.Pedido" %>
<%@ page import="entidades.PedidoProducto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>id Pedido: 99</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>

<body>
	<% Pedido pedido = (Pedido) request.getAttribute("pedido");	%>

	<div class="container">
		<div class="row">
			<div class="col">

				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Producto</th>
							<th scope="col">Cantidad</th>
							<th scope="col">Precio</th>
							<th scope="col">Total</th>
						</tr>
					</thead>
					<tbody>
					
					<% 
						if (pedido != null) {
							for (PedidoProducto detalle: pedido.getDetalle()) {
							
								%>
						<tr>
							<%-- <th scope="row"><%=i+1%></th> --%>
							<%-- <td><%=producto.getDescripcion() %></td>
							<td><%=producto.getPrecio() %></td> --%>
							
						</tr>			
					
					
					
					<%
							}
						}
					
					%>
					
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td colspan="2">Larry the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>

</body>

</html>