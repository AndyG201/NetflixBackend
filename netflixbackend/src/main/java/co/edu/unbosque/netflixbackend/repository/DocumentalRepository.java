package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Documental;

@Repository
public class DocumentalRepository {

    @Autowired
    private ConexionDB conexionDB = new ConexionDB();

    public boolean agregarDocumental(Documental documental) {
        String sql = "INSERT INTO documental (nombre, descripcion, poster, fecha_estreno, calificacion, popularidad, url_documental) "
                   + "VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documental.getNombre());
            ps.setString(2, documental.getDescripcion());
            ps.setString(3, documental.getPoster());
            ps.setString(4, documental.getFechaEstreno());
            ps.setDouble(5, documental.getCalificacion());
            ps.setDouble(6, documental.getPopularidad());
            ps.setString(7, documental.getUrlDocumental());
        
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Documental findByNombre(String nombre) {
        Documental documental = null;
        String sql = "SELECT * FROM documental WHERE nombre = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre.toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    documental = new Documental();
                    documental.setIdDocumental(rs.getInt("id_documental"));
                    documental.setNombre(rs.getString("nombre"));
                    documental.setDescripcion(rs.getString("descripcion"));
                    documental.setPoster(rs.getString("poster"));
                    documental.setFechaEstreno(rs.getString("fecha_estreno"));
                    documental.setCalificacion(rs.getDouble("calificacion"));
                    documental.setPopularidad(rs.getDouble("popularidad"));
                    documental.setUrlDocumental(rs.getString("url_documental"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documental;
    }

    public List<Documental> findAll() {
        List<Documental> documentales = new ArrayList<>();
        String sql = "SELECT * FROM documental";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Documental documental = new Documental();
                documental.setIdDocumental(rs.getInt("id_documental"));
                documental.setNombre(rs.getString("nombre"));
                documental.setDescripcion(rs.getString("descripcion"));
                documental.setPoster(rs.getString("poster"));
                documental.setFechaEstreno(rs.getString("fecha_estreno"));
                documental.setCalificacion(rs.getDouble("calificacion"));
                documental.setPopularidad(rs.getDouble("popularidad"));
                documental.setUrlDocumental(rs.getString("url_documental"));
                documentales.add(documental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentales;
    }

    public List<Documental> findByPopularidad(double minPopularidad) {
        List<Documental> documentales = new ArrayList<>();
        String sql = "SELECT * FROM documental WHERE popularidad >= ? ORDER BY popularidad DESC";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, minPopularidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Documental documental = new Documental();
                    documental.setIdDocumental(rs.getInt("id_documental"));
                    documental.setNombre(rs.getString("nombre"));
                    documental.setDescripcion(rs.getString("descripcion"));
                    documental.setPoster(rs.getString("poster"));
                    documental.setFechaEstreno(rs.getString("fecha_estreno"));
                    documental.setCalificacion(rs.getDouble("calificacion"));
                    documental.setPopularidad(rs.getDouble("popularidad"));
                    documental.setUrlDocumental(rs.getString("url_documental"));
                    documentales.add(documental);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentales;
    }
}
