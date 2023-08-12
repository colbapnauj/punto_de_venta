<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Restaurante - Inicio de sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	background-image: url('img/fondo.jpg'); 
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

body {
	display: flex;
	align-items: center;
	justify-content: center;
}

.container {
	background-color: rgba(200, 255, 200, 0.8); 
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-group label {
	font-size: 20px;
	color: #333; 
}

.form-control {
	font-size: 18px;
}

.btn {
	font-size: 20px;
}

</style>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Inicio de Sesión</h1>
        <form action="LoginServlet" method="post" class="mt-3">
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" name="txtUsername" id="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" name="txtPassword" id="password" class="form-control" required>
            </div>
                 <input type="hidden" name="type" value="login">       
            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
        </form>
    </div>
</body>
</html>

