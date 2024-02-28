<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="co.edu.unbosque.model.EstudianteDTO" %>
<%@ page import="co.edu.unbosque.model.DataMapper" %>
<%@ page import="java.util.List, co.edu.unbosque.model.EstudianteDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
</head>
<body>
    <h1>Student List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Numero de Identificacion</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Edad</th>
                <th>Correo Electronico</th>
            </tr>
        </thead>
        <tbody>
            <%@ SuppressWarnings("unchecked") %>
            <% for (EstudianteDTO estudiante : (List<EstudianteDTO>) request.getAttribute("estudiantes")) { %>
                <tr>
                    <td><%= estudiante.getId() %></td>
                    <td><%= estudiante.getNombres() %></td>
                    <td><%= estudiante.getApellidos() %></td>
                    <td><%= estudiante.getEdad() %></td>
                    <td><%= estudiante.getCorreo() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
