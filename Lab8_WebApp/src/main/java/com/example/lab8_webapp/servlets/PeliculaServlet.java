package com.example.lab8_webapp.servlets;
import com.example.lab8_webapp.beans.Pelicula;
import com.example.lab8_webapp.daos.DaoBase;
import com.example.lab8_webapp.daos.PeliculaDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PeliculaServlet", value = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
      PeliculaDao peliculaDao = new PeliculaDao();
      RequestDispatcher view;

      switch (action) {
         case "lista":
            ArrayList<Pelicula> listaPeliculas = peliculaDao.obtenerListaPeliculas();
            request.setAttribute("listaPeliculas", listaPeliculas);
            view = request.getRequestDispatcher("listaPeliculas.jsp");
            view.forward(request, response);
            break;
         case "eliminar":
            int idPelicula = Integer.parseInt(request.getParameter("id"));
            if (peliculaDao.obtenerPelicula(idPelicula) != null) {
               peliculaDao.eliminarPelicula(idPelicula);
            }
            response.sendRedirect(request.getContextPath() + "/PeliculaServlet");
            break;
      }
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
      PeliculaDao peliculaDao = new PeliculaDao();
      RequestDispatcher view;
      if (action.equals("buscar")) {
         String nombreBusqueda = request.getParameter("nombreBusqueda");
         ArrayList<Pelicula> listaPeliculas = peliculaDao.buscarPelicula(nombreBusqueda);
         request.setAttribute("listaPeliculas", listaPeliculas);
         view = request.getRequestDispatcher("listaPeliculas.jsp");
         view.forward(request, response);
      }
   }
}
