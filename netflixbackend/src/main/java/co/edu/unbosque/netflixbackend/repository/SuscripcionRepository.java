package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Suscripcion;
@Repository
public class SuscripcionRepository {
	
	@Autowired
	private ConexionDB conexionDB = new ConexionDB();
	
	
	public List<Suscripcion> getAll(){
		List<Suscripcion> suscripciones = new ArrayList<>();
		String sql = "SELECT * FROM suscripcion";
		try(Connection conn = conexionDB.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				Suscripcion suscripcion = new Suscripcion();
				suscripcion.setIdSuscripcion(rs.getInt("id_suscripcion"));
				suscripcion.setTipoSuscripcion(rs.getString("tipo_suscripcion"));
				suscripcion.setDuracion(rs.getInt("duracion"));
				suscripcion.setPrecio(rs.getInt("precio"));
				suscripcion.setDescripcion(rs.getString("descripcion"));
				
				suscripciones.add(suscripcion);
			}
			
		} catch (SQLException e) {
			System.err.println("Error al obtener suscripciones: " + e.getMessage());
		}
		
		return suscripciones;
	}

}
