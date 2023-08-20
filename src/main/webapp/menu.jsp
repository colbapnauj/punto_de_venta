<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurante</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	background-color: #ccffcc;
	margin: 0;
	padding: 0;
}

h3 {
	font-style: italic;
	font-family: 'Arial', sans-serif;
}

.image-container {
	position: relative;
}

.image-overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(255, 255, 255, 0.5);
	z-index: -1;
}

.navbar {
	background-color: rgba(204, 255, 204, 0.7);
}
</style>
</head>
<body>
<% String projectName = getServletContext().getContextPath(); %>
	<nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary">
		<div class="container">
			<a class="navbar-brand" href="<%=projectName + "/"%>">Restaurante</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav ml-auto">
					<a class="nav-link active" aria-current="page" href="<%=projectName + "/home" %>">Home</a>
					<a class="nav-link" href="configuracion.jsp">Configuracion</a> <a
						class="nav-link" href="caja.jsp">Caja</a> <a class="nav-link"
						aria-disabled="true" href="<%=getServletContext().getContextPath() + "/LoginServlet?type=logout" %>">Cerrar sesión</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="image-container">
			<div class="image-overlay"></div>
			<h3 style="font-style: italic; font-family: 'Arial', sans-serif;">Bienvenido
				al Restaurante</h3>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
