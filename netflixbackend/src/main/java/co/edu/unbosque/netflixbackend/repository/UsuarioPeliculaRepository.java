package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.UsuarioPelicula;

@Repository
public class UsuarioPeliculaRepository {

	@Autowired
	private ConexionDB conexionDB;
	
	public boolean agregarPeliculaVistas(UsuarioPelicula usPelicula) {
	    String sql = "INSERT INTO usuario_pelicula (fecha_visualizacion, id_pelicula, id_usuario) "
	               + "VALUES (?, ?, ?)";

	    try (Connection con = conexionDB.obtenerConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setTimestamp(1, Timestamp.valueOf(usPelicula.getFechaVisualizacion())); 
	        ps.setInt(2, usPelicula.getIdPelicula());
	        ps.setInt(3, usPelicula.getIdUsuario());

	        int filasInsertadas = ps.executeUpdate();
	        return filasInsertadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
