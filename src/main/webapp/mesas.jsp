<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
.mesa-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.mesa-item {
	text-align: center;
	margin: 10px;
	width: 100px;
}

.mesa-img {
	max-width: 100%;
}

.mesa-nombre {
	font-weight: bold;
	margin-top: 5px;
}
</style>
</head>
<body>
	<%@ include file="menu.jsp"%>


	<div class="mesa-container">
		<%
		int cantidadMesas = 12; 
		%>
		<%
		for (int i = 1; i <= cantidadMesas; i++) {
		%>
		<a href="pedido.jsp">
			<div class="mesa-item" id="mesa<%=i%>">
				<img class="mesa-img" src="img/mesaVerde.png" alt="Mesa <%=i%>">
				<div class="mesa-nombre">
					Mesa
					<%=i%></div>
			</div>
		</a>
		<%
		}
		%>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
		var cantidadMesas = <%= cantidadMesas %>; 
		var mesaEstado = []; 
		for (var i = 0; i < cantidadMesas; i++) {
			mesaEstado.push(0);
		}
		function actualizarMesas() {
			var mesaImgs = document.getElementsByClassName("mesa-img");
			for (var i = 0; i < cantidadMesas; i++) {
				if (mesaEstado[i] === 0) {
					mesaImgs[i].src = "img/mesaVerde.jpg";
				} else {
					mesaImgs[i].src = "img/mesaRoja.jpg";
				}
			}
		}
		actualizarMesas();
		setInterval(function() {
			actualizarMesas();
		}, 3000);
	</script>
</body>
		</html>