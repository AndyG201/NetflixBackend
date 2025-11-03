package co.edu.unbosque.netflixbackend.repository;

import java.sql.*;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.PuntosUsuario;

@Repository
public class PuntosUsuarioRepository {

    @Autowired
    private ConexionDB conexionDB;

    // Verificar si el usuario ya tiene puntos
    public PuntosUsuario obtenerPorIdUsuario(int idUsuario) {
        String sql = "SELECT * FROM puntos_usuario WHERE id_usuario = ?";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PuntosUsuario p = new PuntosUsuario();
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setPuntosTotales(rs.getInt("puntos_totales"));
                Timestamp ts = rs.getTimestamp("ultima_actualizacion");
                if (ts != null) {
                    p.setUltimaActualizacion(ts.toLocalDateTime());
                }
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insertar nuevo registro de puntos
    public boolean crearRegistroPuntos(int idUsuario) {
        String sql = "INSERT INTO puntos_usuario (id_usuario, puntos_totales, ultima_actualizacion) VALUES (?, 0, NOW())";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sumar puntos al usuario
    public boolean sumarPuntos(int idUsuario, int puntos) {
        String sql = "UPDATE puntos_usuario SET puntos_totales = puntos_totales + ?, ultima_actualizacion = NOW() WHERE id_usuario = ?";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, puntos);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
