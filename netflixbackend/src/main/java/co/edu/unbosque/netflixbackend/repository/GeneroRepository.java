package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Genero;

@Repository
public class GeneroRepository {

	@Autowired
	private ConexionDB conexionDB = new ConexionDB();
	
	public boolean crearGeneros(Genero genero) {
	    String sql = "INSERT INTO genero (id_genero, nombre) VALUES (?, ?)";
	    
	    try (Connection con = conexionDB.obtenerConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, genero.getIdGenero());
	        ps.setString(2, genero.getNombre());

	        int filas = ps.executeUpdate();
	        return filas > 0; // true si se insertó al menos una fila

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
