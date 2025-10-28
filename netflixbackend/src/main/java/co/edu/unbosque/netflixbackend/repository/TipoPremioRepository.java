package co.edu.unbosque.netflixbackend.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.TipoPremio;

@Repository
public class TipoPremioRepository {

    @Autowired
    private ConexionDB conexionDB;

    public boolean agregarTipoPremio(TipoPremio tipo) {
        String sql = "INSERT INTO tipo_premio (nombre_tipo, criterio, recompensa) VALUES (?, ?, ?)";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo.getNombreTipo());
            ps.setString(2, tipo.getCriterio());
            ps.setString(3, tipo.getRecompensa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public TipoPremio findById(int idTipoPremio) {
        TipoPremio tipo = null;
        String sql = "SELECT * FROM tipo_premio WHERE id_tipo_premio = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTipoPremio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipo = new TipoPremio();
                    tipo.setIdTipoPremio(rs.getInt("id_tipo_premio"));
                    tipo.setNombreTipo(rs.getString("nombre_tipo"));
                    tipo.setCriterio(rs.getString("criterio"));
                    tipo.setRecompensa(rs.getString("recompensa"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipo;
    }

    public List<TipoPremio> findAll() {
        List<TipoPremio> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_premio";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoPremio tipo = new TipoPremio();
                tipo.setIdTipoPremio(rs.getInt("id_tipo_premio"));
                tipo.setNombreTipo(rs.getString("nombre_tipo"));
                tipo.setCriterio(rs.getString("criterio"));
                tipo.setRecompensa(rs.getString("recompensa"));
                lista.add(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
