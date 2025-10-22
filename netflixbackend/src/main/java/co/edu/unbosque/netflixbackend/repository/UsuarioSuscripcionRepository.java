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
	private static ConexionDB conexionDB = new ConexionDB();
	
	public int adquirirSuscripcion(int codigoSuscripcion, int codigoUsuario) {
	    String sql = "INSERT INTO usuario_suscripcion (fecha_inicio, fecha_fin, id_estado_usu_sus, id_usuario, id_suscripcion) "
	               + "VALUES (?, ?, ?, ?, ?)";

	    int estado = 1;
	    LocalDateTime fechaInicio = LocalDateTime.now();
	    LocalDateTime fechaFin = fechaInicio.plusDays(30);

	    try (Connection con = conexionDB.obtenerConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setTimestamp(1, Timestamp.valueOf(fechaInicio));
	        ps.setTimestamp(2, Timestamp.valueOf(fechaFin));
	        ps.setInt(3, estado);
	        ps.setInt(4, codigoUsuario);
	        ps.setInt(5, codigoSuscripcion);

	        int filasInsertadas = ps.executeUpdate();
	        return filasInsertadas;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	
	public int obtenerDiasSuscripcion (int idSuscripcion) {
		int dias ;
		String sql = "SELECT duracion FROM suscripcion WHERE id_suscripcion = ?";
		
		try(Connection conn = conexionDB.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
			
			dias = rs.getInt("duracion");
			return dias;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
