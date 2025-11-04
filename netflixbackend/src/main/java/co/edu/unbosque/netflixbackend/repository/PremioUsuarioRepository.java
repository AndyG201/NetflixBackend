package co.edu.unbosque.netflixbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.netflixbackend.model.PremioUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PremioUsuarioRepository {

    @Autowired
    private ConexionDB conexionDB;

    // ✅ Obtener premios por usuario
    public List<PremioUsuario> obtenerPremiosPorUsuario(int idUsuario) {
        List<PremioUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM premio_usuario WHERE id_usuario = ?";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PremioUsuario p = new PremioUsuario();
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdPremio(rs.getInt("id_premio"));
                p.setFechaOtorgado(rs.getDate("fecha_otorgado").toLocalDate());
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ✅ Verificar si el usuario ya tiene un premio específico
    public boolean tienePremio(int idUsuario, int idPremio) {
        String sql = "SELECT 1 FROM premio_usuario WHERE id_usuario = ? AND id_premio = ?";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPremio);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si ya existe
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Insertar un nuevo premio
    public boolean asignarPremio(int idUsuario, int idPremio) {
        String sql = "INSERT INTO premio_usuario (id_usuario, id_premio, fecha_otorgado) VALUES (?, ?, NOW())";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPremio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
