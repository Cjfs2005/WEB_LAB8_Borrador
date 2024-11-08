package com.example.lab8_webapp.servlets;

import com.example.lab8_webapp.beans.Genero;
import com.example.lab8_webapp.beans.Pelicula;
import com.example.lab8_webapp.daos.PeliculaDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "detallesServlet", value = "/detallesServlet")
public class detallesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "detalles" : request.getParameter("action");
        PeliculaDao peliculaDao = new PeliculaDao();
        RequestDispatcher view;

        if(action.equals("detalles")){
            Pelicula pelicula = peliculaDao.obtenerPelicula(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("pelicula", pelicula);
            view = request.getRequestDispatcher("viewPelicula.jsp");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "detalles" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();

        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo(request.getParameter("titulo"));
        pelicula.setIdPelicula(Integer.parseInt(request.getParameter("idPelicula")));
        pelicula.setRating(Double.parseDouble(request.getParameter("rating")));
        pelicula.setDirector(request.getParameter("director"));
        pelicula.setAnoPublicacion(Integer.parseInt(request.getParameter("anoPublicacion")));
        pelicula.setBoxOffice(Double.parseDouble(request.getParameter("boxOffice")));
        Genero genero = new Genero();
        genero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
        genero.setNombre(request.getParameter("nombreGenero"));
        pelicula.setGenero(genero);
        if(action.equals("actualizar")){
            peliculaDao.actualizarPelicula(pelicula);
            response.sendRedirect("PeliculaServlet");
        }
    }
}
