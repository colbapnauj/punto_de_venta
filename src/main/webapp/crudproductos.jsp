<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="entidades.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD PRODUCTOS</title>
</head>
<body>
	<h1>Productos</h1>
	
	<%
		List<Producto> listaProductos = (List<Producto>) request.getAttribute("dataProductos");
		// Estudiante estudianteForm = (Estudiante) request.getAttribute("estudianteData");
	%>
	
	<%
	if (listaProductos != null) {
		for (Producto item: listaProductos) {
	%>
		<p><%=item.getDescripcion() %></p>
	<%
						
		}
	}
	%>
</body>
</html>