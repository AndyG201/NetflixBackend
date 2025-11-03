package co.edu.unbosque.netflixbackend.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.netflixbackend.model.PremioUsuario;

@Repository
public class PremioUsuarioRepository {

    @Autowired
    private ConexionDB conexionDB;

    public List<PremioUsuario> obtenerPremiosPorUsuario(int idUsuario) {
        List<PremioUsuario> lista = new ArrayList<>();
        String sql = "SELECT pu.id_usuario, pu.id_premio, p.nombre AS nombrePremio, p.descripcion, " +
                "pu.fecha_otorgado, pu.fecha_reclamado, pu.estado " +
                "FROM premio_usuario pu " +
                "JOIN premio p ON pu.id_premio = p.id_premio " +
                "WHERE pu.id_usuario = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PremioUsuario premio = new PremioUsuario();
                    premio.setIdUsuario(rs.getInt("id_usuario"));
                    premio.setIdPremio(rs.getInt("id_premio"));
                    premio.setNombrePremio(rs.getString("nombrePremio"));
                    premio.setDescripcion(rs.getString("descripcion"));
                    premio.setFechaOtorgado(rs.getString("fecha_otorgado"));
                    premio.setFechaReclamado(rs.getString("fecha_reclamado"));
                    premio.setEstado(rs.getString("estado"));
                    lista.add(premio);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
