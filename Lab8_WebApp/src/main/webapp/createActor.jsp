<%--
  Created by IntelliJ IDEA.
  User: CHRISTIAN
  Date: 7/11/2024
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="com.example.lab8_webapp.beans.Pelicula" scope="request" id="pelicula"/>
<html>
<head>
    <title>Crear actor</title>
    <style>
        th, td,table {
            border-left: 1px solid #000;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
            border-top: 1px solid #000;
        }
    </style>
</head>
<body>
    <h1>Nuevo protagonista de <%=pelicula.getTitulo()%></h1>

    <form method="post" action="actorServlet?action=crear">
        <input type="submit" value="Guardar Actor">
        <table>
            <tr>
                <td style="font-weight: bold">Nombre</td>
                <td><input type="text" name="nombre" id="nombre"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Apellido</td>
                <td><input type="text" name="apellido" id="apellido"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Año Nacimiento</td>
                <td><input type="text" name="anoNacimiento" id="anoNacimiento"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Ganador Premio Oscar</td>
                <td>
                <select name="premioOscar" id="premioOscar">
                    <option value="true">Sí</option>
                    <option value="false">No</option>
                </select>
                </td>
            </tr>
            <input type="hidden" name="idPelicula" id="idPelicula" value="<%= pelicula.getIdPelicula()%>">
        </table>
    </form>
</body>
</html>
