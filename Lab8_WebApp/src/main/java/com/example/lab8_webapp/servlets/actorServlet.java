package com.example.lab8_webapp.servlets;

import com.example.lab8_webapp.beans.Actor;
import com.example.lab8_webapp.beans.Pelicula;
import com.example.lab8_webapp.daos.PeliculaDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "actorServlet", value = "/actorServlet")
public class actorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        PeliculaDao peliculaDao = new PeliculaDao();
        RequestDispatcher view;
        switch (action) {
            case "lista":
                ArrayList<Actor> listaActores = peliculaDao.obtenerListaActores(Integer.parseInt(request.getParameter("id")));
                Pelicula pelicula = peliculaDao.obtenerPelicula(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("listaActores", listaActores);
                request.setAttribute("pelicula", pelicula);
                view = request.getRequestDispatcher("listaActores.jsp");
                view.forward(request, response);
                break;
            case "formCrear":
                Pelicula peli = peliculaDao.obtenerPelicula(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("pelicula", peli);
                view = request.getRequestDispatcher("createActor.jsp");
                view.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        PeliculaDao peliculaDao = new PeliculaDao();
        Actor actor = new Actor();
        if(action.equals("crear")){
            actor.setNombre(request.getParameter("nombre"));
            actor.setApellido(request.getParameter("apellido"));
            actor.setAnoNacimiento(Integer.parseInt(request.getParameter("anoNacimiento")));
            actor.setPremioOscar(Boolean.parseBoolean(request.getParameter("premioOscar")));
            peliculaDao.crearActor(actor);

            Pelicula pelicula = new Pelicula();
            pelicula = peliculaDao.obtenerPelicula(Integer.parseInt(request.getParameter("idPelicula")));

            peliculaDao.crearProtagonista(pelicula,actor);
            response.sendRedirect(request.getContextPath() + "/PeliculaServlet");
        }
    }
}
