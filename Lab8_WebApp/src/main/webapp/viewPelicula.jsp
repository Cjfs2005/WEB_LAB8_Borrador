<%--
  Created by IntelliJ IDEA.
  User: CHRISTIAN
  Date: 7/11/2024
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab8_webapp.beans.Pelicula"%>

<jsp:useBean type="com.example.lab8_webapp.beans.Pelicula" scope="request" id="pelicula"/>
<html>
<head>
    <title>Editar Película</title>
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
    <h1>Película Número <%=pelicula.getIdPelicula()%></h1>
    <form method="post" action="detallesServlet?action=actualizar">
        <input type="submit" value="Guardar Película">
        <table>
            <tr>
                <td style="font-weight: bold">Título</td>
                <td><input type="text" name="titulo" id="titulo" value="<%= pelicula.getTitulo()%>"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Director</td>
                <td><input type="text" name="director" id="director" value="<%= pelicula.getDirector()%>"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Año Publicación</td>
                <td><input type="text" name="anoPublicacion" id="anoPublicacion" value="<%= pelicula.getAnoPublicacion()%>"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Rating</td>
                <td><input type="text" name="rating" id="rating" value="<%= pelicula.getRating() == null ? "--" : pelicula.getRating()%>"></td>
            </tr>
            <tr>
                <td style="font-weight: bold">BoxOffice</td>
                <td><input type="text" name="boxOffice" id="boxOffice" value="<%= pelicula.getBoxOffice() == null ? "--" : pelicula.getBoxOffice()%>"></td>
            </tr>
            <input type="hidden" name="idGenero" id="idGenero" value="<%= pelicula.getGenero().getIdGenero()%>">
            <input type="hidden" name="idPelicula" id="idPelicula" value="<%= pelicula.getIdPelicula()%>">
            <tr>
                <td style="font-weight: bold">Actores</td>
                <td><a href="<%=request.getContextPath()%>/actorServlet?id=<%=pelicula.getIdPelicula()%>">Ver actores</a></td>
            </tr>
        </table>
    </form>
</body>
</html>
