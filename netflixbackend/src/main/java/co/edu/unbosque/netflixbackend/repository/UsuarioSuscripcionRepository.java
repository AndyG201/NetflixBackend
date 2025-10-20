package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioSuscripcionRepository {

	@Autowired
	private static ConexionDB conexionDB = new ConexionDB();
	
	public int adquirirSuscripcion(int codigoSuscripcion, int codigoUsuario) {
	    String sql = "INSERT INTO usuario_suscripcion (estado, id_usuario, id_suscripcion)"
	               + "VALUES (?, ?, ?)";
	    
	    
	     
	    String estado = "pendiente";

	    try (Connection con = conexionDB.obtenerConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	    	ps.setString(1, estado);
	        ps.setInt(2, codigoUsuario);
	        ps.setInt(3, codigoSuscripcion);

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
