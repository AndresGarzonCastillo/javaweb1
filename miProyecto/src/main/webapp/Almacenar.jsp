<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Student</title>
</head>
<body>
    <h1>Create Student</h1>
    <form action="estudiantes?action=almacenar" method="post">
        <label for="identificacion">Numero de Identificacion:</label>
        <input type="text" id="identificacion" name="identificacion" required><br>
        <label for="nombres">Nombres:</label>
        <input type="text" id="nombres" name="nombres" required><br>
        <label for="apellidos">Apellidos:</label>
        <input type="text" id="apellidos" name="apellidos" required><br>
        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required><br>
        <label for="correo">Correo electronico:</label>
        <input type="email" id="correo" name="correo" required><br>
        <input type="submit" value="Almacenar">
    </form>
</body>
</html>
