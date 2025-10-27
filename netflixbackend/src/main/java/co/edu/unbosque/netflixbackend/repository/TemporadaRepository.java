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

import co.edu.unbosque.netflixbackend.model.Temporada;

@Repository
public class TemporadaRepository {

    @Autowired
    private ConexionDB conexionDB = new ConexionDB();


    public int insertarTemporada(Temporada temporada) throws SQLException {
    	String sql = "INSERT INTO temporada (numero_temporada, anio_estreno, id_serie) VALUES (?, ?, ?)";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, temporada.getNumeroTemporada());
            ps.setInt(2, temporada.getAnioEstreno());
            ps.setInt(3, temporada.getIdSerie());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                }
            }
        }
        return -1;
    }


    public List<Temporada> obtenerTodasLasTemporadas() {
        List<Temporada> temporadas = new ArrayList<>();
        String sql = "SELECT * FROM temporada";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Temporada temporada = new Temporada();
                temporada.setIdTemporada(rs.getInt("id_temporada"));
                temporada.setNumeroTemporada(rs.getInt("numero_temporada"));
                temporada.setAnioEstreno(rs.getInt("anio_estreno"));
                temporada.setIdSerie(rs.getInt("id_serie"));

                temporadas.add(temporada);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener temporadas: " + e.getMessage());
        }

        return temporadas;
    }

    // OBTENER temporadas por ID de serie
    public List<Temporada> obtenerTemporadasPorSerie(int idSerie) {
        List<Temporada> temporadas = new ArrayList<>();
        String sql = "SELECT * FROM temporada WHERE id_serie = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSerie);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Temporada temporada = new Temporada();
                    temporada.setIdTemporada(rs.getInt("id_temporada"));
                    temporada.setNumeroTemporada(rs.getInt("numero_temporada"));
                    temporada.setAnioEstreno(rs.getInt("anio_estreno"));
                    temporada.setIdSerie(rs.getInt("id_serie"));

                    temporadas.add(temporada);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener temporadas por serie: " + e.getMessage());
        }

        return temporadas;
    }


    public void actualizarTemporada(Temporada temporada) {
        String sql = "UPDATE temporada SET numero_temporada = ?, anio_estreno = ?, id_serie = ? WHERE id_temporada = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, temporada.getNumeroTemporada());
            ps.setInt(2, temporada.getAnioEstreno());
            ps.setInt(3, temporada.getIdSerie());
            ps.setInt(4, temporada.getIdTemporada());

            ps.executeUpdate();
            System.out.println("✅ Temporada actualizada correctamente: ID " + temporada.getIdTemporada());

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar temporada: " + e.getMessage());
        }
    }

    // ELIMINAR temporada por ID
    public void eliminarTemporada(int idTemporada) {
        String sql = "DELETE FROM temporada WHERE id_temporada = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTemporada);
            ps.executeUpdate();
            System.out.println("🗑️ Temporada eliminada con ID: " + idTemporada);

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar temporada: " + e.getMessage());
        }
    }

    public Temporada obtenerTemporadaPorId(int idTemporada) {
        Temporada temporada = null;
        String sql = "SELECT * FROM temporada WHERE id_temporada = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTemporada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    temporada = new Temporada();
                    temporada.setIdTemporada(rs.getInt("id_temporada"));
                    temporada.setNumeroTemporada(rs.getInt("numero_temporada"));
                    temporada.setAnioEstreno(rs.getInt("anio_estreno"));
                    temporada.setIdSerie(rs.getInt("id_serie"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener temporada: " + e.getMessage());
        }

        return temporada;
    }
}
