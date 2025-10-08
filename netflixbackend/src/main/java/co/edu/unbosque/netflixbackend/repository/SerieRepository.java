package co.edu.unbosque.netflixbackend.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unbosque.netflixbackend.model.Serie;


public class SerieRepository {
	
	@Autowired
	private ConexionDB conexionDB = new ConexionDB();

    
    public void insertarSerie(Serie serie) {
        String sql = "INSERT INTO serie (nombre, genero, descripcion, id_clasificacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serie.getNombre());
            ps.setString(2, serie.getGenero());
            ps.setString(3, serie.getDescripcion());
            ps.setInt(4, serie.getIdClasificacion());

            ps.executeUpdate();
            System.out.println("Serie insertada correctamente: " + serie.getNombre());

        } catch (SQLException e) {
            System.err.println("Error al insertar serie: " + e.getMessage());
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
                serie.setNombre(rs.getString("nombre"));
                serie.setGenero(rs.getString("genero"));
                serie.setDescripcion(rs.getString("descripcion"));
                serie.setIdClasificacion(rs.getInt("id_clasificacion"));

                series.add(serie);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener series: " + e.getMessage());
        }

        return series;
    }

    // ELIMINAR una serie por ID
    public void eliminarSerie(int idSerie) {
        String sql = "DELETE FROM serie WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSerie);
            ps.executeUpdate();
            System.out.println("Serie eliminada con ID: " + idSerie);

        } catch (SQLException e) {
            System.err.println("Error al eliminar serie: " + e.getMessage());
        }
    }

    // ACTUALIZAR datos de una serie
    public void actualizarSerie(Serie serie) {
        String sql = "UPDATE serie SET nombre = ?, genero = ?, descripcion = ?, id_clasificacion = ? WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serie.getNombre());
            ps.setString(2, serie.getGenero());
            ps.setString(3, serie.getDescripcion());
            ps.setInt(4, serie.getIdClasificacion());
            ps.setInt(5, serie.getIdSerie());

            ps.executeUpdate();
            System.out.println("Serie actualizada correctamente: " + serie.getNombre());

        } catch (SQLException e) {
            System.err.println("Error al actualizar serie: " + e.getMessage());
        }
    }
}
