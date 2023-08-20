<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="entidades.Mesa"%>
<%@ page import="java.util.List"%>
<%@ page import="modelos.MesaModelo"%>
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
	<%@ include file="menu.jsp"%>
	<div class="mesa-container">
		<%
		List<Mesa> listaMesas = (List<Mesa>) request.getAttribute("listaMesas");
		List<Integer> estados = (List<Integer>) request.getAttribute("estados");
		if (listaMesas != null && !listaMesas.isEmpty()) {

			for (int i = 0; i < listaMesas.size(); i++) {
				Mesa mesa = listaMesas.get(i);
				int estado = estados.get(i);
		%>
		<a href="pedido.jsp?mesa=<%=i + 1%>">
			<div class="mesa-item" id="mesa<%=i + 1%>">
				<div class="mesa-overlay"></div>
				<img class="mesa-img" id="mesaImg<%=i + 1%>" alt="Mesa <%=i + 1%>">
			</div>
		</a>
		<script>
            var mesaImg<%=i + 1%> = document.getElementById("mesaImg<%=i + 1%>
			");
			mesaImg
		<%=i + 1%>
			.src =
		<%=estado == 1 ? "'img/mesaVerde.png'" : "'img/mesaRoja.png'"%>
			;
		</script>
		<%
		}
		} else {
		%>
		<p>No hay mesas disponibles.</p>
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
		for (var i = 0; i < estados.length; i++) {
			var mesaImg = document.getElementById("mesaImg" + (i + 1));
			mesaImg.src = estados[i] === 1 ? 'img/mesaVerde.png'
					: 'img/mesaRoja.png';
		}
	</script>
</body>
</html>
