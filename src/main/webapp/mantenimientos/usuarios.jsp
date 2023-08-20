<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Empleado"%>
<%@ page import="modelos.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento usuario</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	background-image: url('../img/fondo.jpg');
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

.container3 {
	background-color: rgba(200, 255, 200, 0.8);
	/* Color de fondo verde claro */
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>

<body
	style="background-image: url('../img/fondo.jpg'); background-size: cover; background-repeat: repeat-y; width: 100%; height: 100%; margin: 0; padding: 0;">
	<%@ include file="submenu.jsp"%>
	<br>

	<div class="container3">

		<h1>Usuarios</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nombres</th>
					<th scope="col">Usuario</th>
					<th scope="col">Rol</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				UsuarioModelo usuarioModelo = new UsuarioModelo();
			    RolModelo rolModelo = new RolModelo();
				List<Empleado> listaEmpleados = usuarioModelo.obtenerEmpleados();

				int contador = 1;
				for (Empleado empleado : listaEmpleados) {
			        String nombreRol = rolModelo.obtenerRol(String.valueOf(empleado.getIdRole())).getNombre();

				%>
				<tr>
					<th scope="row"><%=contador%></th>
					<td><%=empleado.getNombre()%></td>
					<td><%=empleado.getUsuario()%></td>
					<td><%= nombreRol %></td> 
					<td><button class="btn btn-primary btn-sm">Editar</button></td>
				</tr>
				<%
				contador++;
				}
				%>
			</tbody>
		</table>
		<form method="post" action="ruta_para_procesar_creacion_de_usuario">
			<h4>Registrar Usuario</h4>
			<div class="mb-3">
				<label for="inputNames" class="form-label">Nombres</label> <input
					type="text" class="form-control" id="inputNames" name="nombre"
					aria-describedby="namesHelp">
				<div id="namesHelp" class="form-text">Ingrese sus nombres
					completos</div>
			</div>
			<div class="mb-3">
				<label for="inputUsername" class="form-label">Usuario</label> <input
					type="text" class="form-control" id="inputUsername" name="usuario"
					aria-describedby="usernameHelp" maxlength="10">
				<div id="usernameHelp" class="form-text">Máximo 10 caracteres</div>
			</div>
			<div class="mb-3">
				<label for="inputPassword" class="form-label">Contraseña</label> <input
					type="password" class="form-control" id="inputPassword"
					name="contrasena">
			</div>
			<div class="mb-3">
				<label for="inputRole" class="form-label">Rol</label> <select
					class="form-select" id="inputRole" name="idRole">
					<option value="1">Admin</option>
					<option value="2">Cajero</option>
					<option value="3">Mozo</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Guardar</button>
		</form>


		<div></div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>