<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurante</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<!-- Barra de navegación -->
	<nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary">
		<div class="container">
			<a class="navbar-brand" href="#">Restaurante</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav ml-auto">
					<a class="nav-link active" aria-current="page" href="mesas.jsp">Home</a>
					<a class="nav-link" href="configuracion.jsp">Configuracion</a> 
					<a class="nav-link" href="caja.jsp">Caja</a> 
					<a class="nav-link" aria-disabled="true" href="index.jsp">Cerrar
						sesión</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-5" >
		<h3>Bienvenido al Restaurante</h3>
		<!-- Otro contenido aquí -->
	</div>

	<!-- Scripts de Bootstrap y otros archivos JS aquí -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>