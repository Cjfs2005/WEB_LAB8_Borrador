<%--
  Created by IntelliJ IDEA.
  User: CHRISTIAN
  Date: 7/11/2024
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab8_webapp.beans.Actor"%>

<jsp:useBean type="java.util.ArrayList<com.example.lab8_webapp.beans.Actor>" scope="request" id="listaActores"/>
<jsp:useBean type="com.example.lab8_webapp.beans.Pelicula" scope="request" id="pelicula"/>
<html lang="es">
<head>
    <title><%=pelicula.getTitulo()%></title>
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
<h1><%=pelicula.getTitulo()%></h1>
    <table>
        <tr>
            <th>idActor</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Ano Nacimiento</th>
            <th>Ganador Premio Oscar</th>
        </tr>
        <% for( Actor actor:listaActores){ %>
        <tr>
            <td><%=actor.getIdActor()%></td>
            <td><%=actor.getNombre()%></td>
            <td><%=actor.getApellido()%></td>
            <td><%=actor.getAnoNacimiento()%></td>
            <td><%=actor.isPremioOscar()%></td>
        </tr>
        <% } %>
    </table>
    <button><a href="<%=request.getContextPath()%>/actorServlet?action=formCrear&id=<%=pelicula.getIdPelicula()%>" style="text-decoration: none">Crear Actor</a></button>
</body>
</html>
