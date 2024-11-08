package com.example.lab8_webapp.daos;

import com.example.lab8_webapp.beans.Pelicula;
import com.example.lab8_webapp.beans.Genero;
import com.example.lab8_webapp.beans.Actor;
import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao extends DaoBase{

    public ArrayList<Pelicula> obtenerListaPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, \n" +
                "       g.idGenero, g.nombre AS nombreGenero \n" +
                "FROM Pelicula p \n" +
                "JOIN Genero g ON p.idGenero = g.idGenero\n" +
                "ORDER BY p.rating DESC, p.boxOffice DESC;";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombreGenero"));
                pelicula.setGenero(genero);

                listaPeliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPeliculas;
    }


    public Pelicula obtenerPelicula(int idPelicula) {
        Pelicula pelicula = null;
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, " +
                "g.idGenero, g.nombre as nombreGenero " +
                "FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                    pelicula.setRating(rs.getDouble("rating"));
                    pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("idGenero"));
                    genero.setNombre(rs.getString("nombreGenero"));
                    pelicula.setGenero(genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelicula;
    }


    public void eliminarPelicula(int idPelicula) {
        String eliminarProtagonistasSql = "DELETE FROM Protagonistas WHERE idPelicula = ?";
        String eliminarPeliculaSql = "DELETE FROM Pelicula WHERE idPelicula = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmtProtagonistas = conn.prepareStatement(eliminarProtagonistasSql);
             PreparedStatement pstmtPelicula = conn.prepareStatement(eliminarPeliculaSql)) {

            // Primero se deben eliminar registros en Protagonistas
            pstmtProtagonistas.setInt(1, idPelicula);
            pstmtProtagonistas.executeUpdate();

            // Y luego eliminar la película en la tabla Pelicula
            pstmtPelicula.setInt(1, idPelicula);
            pstmtPelicula.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Pelicula> buscarPelicula(String nombrePelicula) {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, " +
                "g.idGenero, g.nombre as nombreGenero " +
                "FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE LOWER(p.titulo) LIKE ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombrePelicula.toLowerCase() + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                    pelicula.setRating(rs.getDouble("rating"));
                    pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                    Genero genero = new Genero();
                    genero.setIdGenero(rs.getInt("idGenero"));
                    genero.setNombre(rs.getString("nombreGenero"));
                    pelicula.setGenero(genero);

                    listaPeliculas.add(pelicula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPeliculas;
    }

    public void actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE Pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ?, idGenero = ? " +
                "WHERE idPelicula = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnoPublicacion());

            // Manejo para valores nulos en rating y boxOffice
            if (pelicula.getRating() != null) {
                pstmt.setDouble(4, pelicula.getRating());
            } else {
                pstmt.setNull(4, Types.DOUBLE);
            }

            if (pelicula.getBoxOffice() != null) {
                pstmt.setDouble(5, pelicula.getBoxOffice());
            } else {
                pstmt.setNull(5, Types.DOUBLE);
            }

            pstmt.setInt(6, pelicula.getGenero().getIdGenero());
            pstmt.setInt(7, pelicula.getIdPelicula());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Actor> obtenerListaActores(int idPelicula) {
        ArrayList<Actor> listaActores = new ArrayList<>();
        String sql = "SELECT a.idActor, a.Nombre, a.Apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a " +
                "JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = new Actor();
                    actor.setIdActor(rs.getInt("idActor"));
                    actor.setNombre(rs.getString("Nombre"));
                    actor.setApellido(rs.getString("Apellido"));
                    actor.setAnoNacimiento(rs.getInt("anoNacimiento"));
                    actor.setPremioOscar(rs.getBoolean("premioOscar"));

                    listaActores.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaActores;
    }

    public void crearActor(Actor actor) {
        String sql = "INSERT INTO Actor (Nombre, Apellido, anoNacimiento, premioOscar) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, actor.getNombre());
            pstmt.setString(2, actor.getApellido());
            pstmt.setInt(3, actor.getAnoNacimiento());
            pstmt.setBoolean(4, actor.isPremioOscar());

            pstmt.executeUpdate();

            // Obtener y asignar el ID generado al actor
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    actor.setIdActor(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearProtagonista(Pelicula pelicula, Actor actor) {
        String sql = "INSERT INTO Protagonistas (idPelicula, idActor) VALUES (?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pelicula.getIdPelicula());
            pstmt.setInt(2, actor.getIdActor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
