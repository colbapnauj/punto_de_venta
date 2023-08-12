<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
	position: relative;
}

.mesa-img {
	max-width: 100%;
}

.mesa-overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(255, 255, 255, 0.7);
	z-index: -1;
	border-radius: 10px;
}

.mesa-nombre {
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
	>
	<%@ include file="menu.jsp"%>
	<div class="mesa-container">
		<%
		int cantidadMesas = 12;
		%>
		<%
		for (int i = 1; i <= cantidadMesas; i++) {
		%>
		<a href="pedido.jsp?mesa=<%=i%>">
			<div class="mesa-item" id="mesa<%=i%>">
				<div class="mesa-overlay"></div>
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
		var cantidadMesas =
	<%=cantidadMesas%>
		;
		var mesaEstado = [];
		for (var i = 0; i < cantidadMesas; i++) {
			mesaEstado.push(0);
		}
		function actualizarMesas() {
			var mesaImgs = document.getElementsByClassName("mesa-img");
			for (var i = 0; i < cantidadMesas; i++) {
				if (mesaEstado[i] === 0) {
					mesaImgs[i].src = "img/mesaVerde.png";
				} else {
					mesaImgs[i].src = "img/mesaRoja.png";
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
