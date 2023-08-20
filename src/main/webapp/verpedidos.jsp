<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="modelos.PedidoModelo"%>
<%@ page import="entidades.Pedido"%>
<%@ page import="entidades.PedidoProducto"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Ver Pedidos</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	background-image: url('img/fondo.jpg');
	/* Cambia 'ruta-de-tu-imagen.jpg' por la URL de tu imagen de fondo */
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

.container2 {
	background-color: rgba(255, 255, 255, 0.9);
	/* Fondo blanco transparente */
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body
	style="background-image: url('img/fondo.jpg'); background-size: cover; background-repeat: repeat-y; width: 100%; height: 100%; margin: 0; padding: 0;">
	<%@ include file="menu.jsp"%>
	<div class="container2 mt-5">
		<h3>Lista de Pedidos</h3>
		<table class="table">
			<thead>
				<tr>
					<th>ID Pedido</th>
					<th>ID Empleado</th>
					<th>ID Mesa</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				PedidoModelo pedidoModelo = new PedidoModelo();
				List<Pedido> listaPedidos = pedidoModelo.obtenerPedidos();

				for (Pedido pedido : listaPedidos) {
				%>
				<tr>
					<td><%=pedido.getIdPedido()%></td>
					<td><%=pedido.getIdEmpleado()%></td>
					<td><%=pedido.getIdMesa()%></td>
					<td><a
						href="verpedidos.jsp?idPedido=<%=pedido.getIdPedido()%>#pedido-details"
						class="ver-detalles">Ver Detalles</a></td>

				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

<div class="container mt-3">
    <div id="pedido-details">
        <!-- Contenido de los detalles del pedido se generará aquí -->
        <%
        String idPedidoParameter = request.getParameter("idPedido");
        out.println("idPedido Parameter: " + idPedidoParameter);

        if (idPedidoParameter != null) {
            try {
                int idPedidoSeleccionado = Integer.parseInt(idPedidoParameter);
                Pedido pedidoSeleccionado = null;
                for (Pedido pedido : listaPedidos) {
                    if (pedido.getIdPedido() == idPedidoSeleccionado) {
                        pedidoSeleccionado = pedido;
                        break;
                    }
                }

                if (pedidoSeleccionado != null && pedidoSeleccionado.getDetalle() != null
                        && !pedidoSeleccionado.getDetalle().isEmpty()) {
        %>
        <h4>Detalles del Pedido <%= pedidoSeleccionado.getIdPedido() %></h4>
        <ul>
            <%
            for (PedidoProducto detalle : pedidoSeleccionado.getDetalle()) {
            %>
            <li>Producto: <%= detalle.getProducto().getDescripcion() %>, Cantidad: <%= detalle.getCantidad() %></li>
            <%
            }
            %>
        </ul>
        <hr>
        <%
                }
            } catch (NumberFormatException e) {
                out.println("Error parsing idPedido: " + e.getMessage());
            }
        }
        %>
    </div>
</div>

</body>
</html>
