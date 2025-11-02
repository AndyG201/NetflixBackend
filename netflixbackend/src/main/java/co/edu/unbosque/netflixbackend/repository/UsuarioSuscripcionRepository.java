package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioSuscripcionRepository {

    @Autowired
    private ConexionDB conexionDB;

    public int adquirirSuscripcion(int codigoSuscripcion, int codigoUsuario) {
        String insertSQL = "INSERT INTO usuario_suscripcion (fecha_inicio, fecha_fin, id_estado_usu_sus, id_usuario, id_suscripcion) "
                + "VALUES (?, ?, ?, ?, ?)";
        String updateSQL = "UPDATE usuario SET id_suscripcion_actual = ? WHERE id_usuario = ?";

        int estado = 1;
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusDays(obtenerDiasSuscripcion(codigoSuscripcion));

        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement psInsert = con.prepareStatement(insertSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateSQL)) {

            // Inserta la suscripción
            psInsert.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            psInsert.setTimestamp(2, Timestamp.valueOf(fechaFin));
            psInsert.setInt(3, estado);
            psInsert.setInt(4, codigoUsuario);
            psInsert.setInt(5, codigoSuscripcion);

            int filasInsertadas = psInsert.executeUpdate();

            // Actualiza el usuario si se insertó correctamente
            if (filasInsertadas > 0) {
                psUpdate.setInt(1, codigoSuscripcion);
                psUpdate.setInt(2, codigoUsuario);
                psUpdate.executeUpdate();
            }

            return filasInsertadas;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int obtenerDiasSuscripcion(int idSuscripcion) {
        String sql = "SELECT duracion FROM suscripcion WHERE id_suscripcion = ?";
        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSuscripcion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("duracion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
