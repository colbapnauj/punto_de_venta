<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento usuario</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<div class="container">
 
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
    <tr>
      <th scope="row">1</th>
      <td>Jean Chiclla</td>
      <td>colbapnauj</td>
      <td>admin</td>
      <td><button class="btn btn-primary btn-sm">Editar</button></td>
    </tr>
  </tbody>
</table>
<form>
  <h4>Registrar Usuario</h4>
  <div class="mb-3">
    <label for="inputNames" class="form-label">Nombres</label>
    <input type="email" class="form-control" id="inputNames" aria-describedby="namesHelp">
    <div id="namesHelp" class="form-text">Ingrese sus nombre completos</div>
  </div>
  <div class="row">
	<div class="mb-3 col-md-6">
	  <label for="inputUsername" class="form-label">Usuario</label>
	  <input type="password" class="form-control" id="inputUsername" aria-describedby="usernameHelp">
	  <div id="usernameHelp" class="form-text">máximo 10 caracteres</div>
	</div>
	<div class="mb-3 col-md-6">
	  <label for="inputRole" class="form-label">Rol</label>
	  <select class="form-select">
	  	<option></option>
	  	<option value="1">Admin</option>
	  	<option value="2">Cajero</option>
	  	<option value="3">Mozo</option>
	  </select>
	</div>
  </div>
  <button type="submit" class="btn btn-primary">Guardar</button>
  
</form>
<div>

</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>	
</body>
</html>