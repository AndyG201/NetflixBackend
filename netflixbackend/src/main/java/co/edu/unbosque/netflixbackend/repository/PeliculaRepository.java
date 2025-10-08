package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Pelicula;

@Repository
public class PeliculaRepository {
	
	@Autowired
	private ConexionDB conexionDB = new ConexionDB();
	
	public boolean agregarPelicula(Pelicula pelicula) {
	    String sql = "INSERT INTO pelicula (nombre, descripcion, poster, fechaEstreno, calificacion, popularidad, idClasificacion) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, pelicula.getNombre());
	        ps.setString(2, pelicula.getDescripcion());
	        ps.setString(3, pelicula.getPoster());
	        ps.setString(4, pelicula.getFechaEstreno());
	        ps.setDouble(5, pelicula.getCalificacion());
	        ps.setDouble(6, pelicula.getPopularidad());
	        ps.setInt(7, pelicula.getIdClasificacion());

	        int filasInsertadas = ps.executeUpdate();
	        return filasInsertadas > 0; // Devuelve true si se insertó correctamente

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public Pelicula findByNombre(String nombre) {
	    Pelicula pelicula = null;
	    String sql = "SELECT * FROM pelicula WHERE nombre = ?";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, nombre.toLowerCase());

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                pelicula = new Pelicula();
	                pelicula.setIdPelicula(rs.getInt("idPelicula"));
	                pelicula.setNombre(rs.getString("nombre"));
	                pelicula.setDescripcion(rs.getString("descripcion"));
	                pelicula.setPoster(rs.getString("poster"));
	                pelicula.setFechaEstreno(rs.getString("fechaEstreno"));
	                pelicula.setCalificacion(rs.getDouble("calificacion"));
	                pelicula.setPopularidad(rs.getDouble("popularidad"));
	                pelicula.setIdClasificacion(rs.getInt("idClasificacion"));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pelicula;
	}
	public List<Pelicula> findAll() {
	    List<Pelicula> peliculas = new ArrayList<>();
	    String sql = "SELECT * FROM pelicula";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Pelicula pelicula = new Pelicula();
	            pelicula.setIdPelicula(rs.getInt("idPelicula"));
	            pelicula.setNombre(rs.getString("nombre"));
	            pelicula.setDescripcion(rs.getString("descripcion"));
	            pelicula.setPoster(rs.getString("poster"));
	            pelicula.setFechaEstreno(rs.getString("fechaEstreno"));
	            pelicula.setCalificacion(rs.getDouble("calificacion"));
	            pelicula.setPopularidad(rs.getDouble("popularidad"));
	            pelicula.setIdClasificacion(rs.getInt("idClasificacion"));
	            peliculas.add(pelicula);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return peliculas;
	}
	
	public List<Pelicula> findByPopularidad(double minPopularidad) {
	    List<Pelicula> peliculas = new ArrayList<>();
	    String sql = "SELECT * FROM pelicula WHERE popularidad >= ? ORDER BY popularidad DESC";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setDouble(1, minPopularidad);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Pelicula pelicula = new Pelicula();
	                pelicula.setIdPelicula(rs.getInt("idPelicula"));
	                pelicula.setNombre(rs.getString("nombre"));
	                pelicula.setDescripcion(rs.getString("descripcion"));
	                pelicula.setPoster(rs.getString("poster"));
	                pelicula.setFechaEstreno(rs.getString("fechaEstreno"));
	                pelicula.setCalificacion(rs.getDouble("calificacion"));
	                pelicula.setPopularidad(rs.getDouble("popularidad"));
	                pelicula.setIdClasificacion(rs.getInt("idClasificacion"));
	                peliculas.add(pelicula);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return peliculas;
	}



}
