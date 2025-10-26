package co.edu.unbosque.netflixbackend.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Serie;

@Repository
public class SerieRepository {

    @Autowired
    private ConexionDB conexionDB = new ConexionDB();

    public void insertarSerie(Serie serie) throws SQLException {
    	String sql = "INSERT INTO serie (titulo, descripcion, fecha_estreno, poster, calificacion, popularidad) VALUES (?, ?, ?, ?, ?, ?)";
    	try (Connection conn = conexionDB.obtenerConexion();
    	     PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

    	    ps.setString(1, serie.getTitulo());
    	    ps.setString(2, serie.getDescripcion());
    	    ps.setString(3, serie.getFechaEstreno());
    	    ps.setString(4, serie.getPoster());
    	    ps.setDouble(5, serie.getCalificacion());
    	    ps.setInt(6, serie.getPopularidad());
    	    ps.executeUpdate();

    	    try (ResultSet rs = ps.getGeneratedKeys()) {
    	        if (rs.next()) {
    	            int idSerieGenerado = rs.getInt(1);
    	            serie.setIdSerie(idSerieGenerado);
    	        }
    	    }
    	}

    }

    // OBTENER todas las series
    public List<Serie> obtenerTodasLasSeries() {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM serie";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Serie serie = new Serie();
                serie.setIdSerie(rs.getInt("id_serie"));
                serie.setTitulo(rs.getString("titulo"));
                serie.setDescripcion(rs.getString("descripcion"));
                serie.setFechaEstreno(rs.getString("fecha_estreno"));
                serie.setPoster(rs.getString("poster"));
                serie.setCalificacion(rs.getDouble("calificacion"));
                serie.setPopularidad(rs.getInt("popularidad"));

                series.add(serie);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener series: " + e.getMessage());
        }

        return series;
    }

    // ACTUALIZAR datos de una serie
    public void actualizarSerie(Serie serie) {
        String sql = "UPDATE serie SET titulo = ?, descripcion = ?, fecha_estreno = ?, poster = ?, calificacion = ?, popularidad = ? WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serie.getTitulo());
            ps.setString(2, serie.getDescripcion());
            ps.setString(3, serie.getFechaEstreno());
            ps.setString(4, serie.getPoster());
            ps.setDouble(5, serie.getCalificacion());
            ps.setInt(6, serie.getPopularidad());
            ps.setInt(7, serie.getIdSerie());

            ps.executeUpdate();
            System.out.println("✅ Serie actualizada correctamente: " + serie.getTitulo());

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar serie: " + e.getMessage());
        }
    }

    // ELIMINAR una serie por ID
    public void eliminarSerie(int idSerie) {
        String sql = "DELETE FROM serie WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSerie);
            ps.executeUpdate();
            System.out.println("🗑️ Serie eliminada con ID: " + idSerie);

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar serie: " + e.getMessage());
        }
    }

    // BUSCAR una serie por ID
    public Serie obtenerSeriePorId(int idSerie) {
        Serie serie = null;
        String sql = "SELECT * FROM serie WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSerie);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    serie = new Serie();
                    serie.setIdSerie(rs.getInt("id_serie"));
                    serie.setTitulo(rs.getString("titulo"));
                    serie.setDescripcion(rs.getString("descripcion"));
                    serie.setFechaEstreno(rs.getString("fecha_estreno"));
                    serie.setPoster(rs.getString("poster"));
                    serie.setCalificacion(rs.getDouble("calificacion"));
                    serie.setPopularidad(rs.getInt("popularidad"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar serie: " + e.getMessage());
        }

        return serie;
    }
}
