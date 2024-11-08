<%--
  Created by IntelliJ IDEA.
  User: CHRISTIAN
  Date: 7/11/2024
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab8_webapp.beans.Pelicula"%>
<%@ page import="java.text.DecimalFormat" %>

<jsp:useBean type="java.util.ArrayList<com.example.lab8_webapp.beans.Pelicula>" scope="request" id="listaPeliculas"/>
<html lang="es">
<head>
    <title>Películas</title>
    <style>
      .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
      }

      .modal-content {
        background-color: #fff;
        margin: 15% auto;
        padding: 5px;
        border: 1px solid #000;
        width: 80%;
        max-width: 300px;
        text-align: center;
      }

      th, td,table {
        border-left: 1px solid #000;
        border-right: 1px solid #000;
        border-bottom: 1px solid #000;
        border-top: 1px solid #000;
      }
    </style>
    <script>
      let eliminarUrl;

      function abrirModal(url) {
        eliminarUrl = url;
        document.getElementById("modal").style.display = "block";
      }

      function cerrarModal() {
        document.getElementById("modal").style.display = "none";
      }

      function confirmarEliminacion() {
        window.location.href = eliminarUrl;
      }
    </script>
</head>
<body>
    <h1>Lista de películas</h1>
    <form method="post" action="PeliculaServlet?action=buscar">
      <input type="text" name="nombreBusqueda" id="nombreBusqueda">
      <input type="submit" value="Buscar">
    </form>
      <table>
        <tr>
          <th>Título</th>
          <th>Director</th>
          <th>Año Publicación</th>
          <th>Rating</th>
          <th>BoxOffice</th>
          <th>Género</th>
          <th>Actores</th>
          <th>Accionable</th>
        </tr>
        <%
          DecimalFormat df = new DecimalFormat("#,###.##");
        %>
        <% for(Pelicula pelicula:listaPeliculas){ %>
        <tr>
          <td><a href="<%=request.getContextPath()%>/detallesServlet?id=<%=pelicula.getIdPelicula()%>"><%=pelicula.getTitulo()%></a></td>
          <td><%=pelicula.getDirector()%></td>
          <td><%=pelicula.getAnoPublicacion()%></td>
          <td><%=pelicula.getRating()%>/10</td>
          <td>$ <%= df.format(pelicula.getBoxOffice()) %></td>
          <td><%=pelicula.getGenero().getNombre()%></td>
          <td><a href="<%=request.getContextPath()%>/actorServlet?id=<%=pelicula.getIdPelicula()%>">Ver actores</a></td>
          <td><a href="#" onclick="abrirModal('<%=request.getContextPath()%>/PeliculaServlet?action=eliminar&id=<%=pelicula.getIdPelicula()%>'); return false;">Eliminar</a></td>
        </tr>
        <% } %>
      </table>

      <div id="modal" class="modal">
        <div class="modal-content">
          <p>¿Estás seguro de que deseas eliminar esta película?</p>
          <div class="modal-buttons">
            <button onclick="confirmarEliminacion()">Confirmar</button>
            <button onclick="cerrarModal()">Cancelar</button>
          </div>
        </div>
      </div>
</body>
</html>
