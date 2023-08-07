<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Restaurante - Inicio de sesión</title>
<!-- Enlaces a Bootstrap CSS -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
/* Estilos personalizados para centrar verticalmente y aumentar el tamaño del texto */
html, body {
	height: 100%;
}

body {
	display: flex;
	align-items: center;
	justify-content: center;
}



.form-group label {
	font-size: 20px;
	/* Tamaño de fuente para las etiquetas de los campos */
}

.form-control {
	font-size: 18px; /* Tamaño de fuente para los campos de entrada */
}

.btn {
	font-size: 20px; /* Tamaño de fuente para el botón */
}
</style>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Inicio de Sesión</h1>
        <form action="login" method="post" class="mt-3">
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" name="username" id="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" name="password" id="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
        </form>
    </div>
</body>
</html>
