package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.MetodoPago;

@Repository
public class MetodoPagoRepository {

	@Autowired
	private ConexionDB conexionDB;
	
	public List<MetodoPago> obtenerMetodosDePago() {
	    List<MetodoPago> lista = new ArrayList<>();
	    String sql = "SELECT * FROM metodo_pago;";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id_metodo_pago");
	            String nombre = rs.getString("nombre_metodo");

	            MetodoPago metodo = new MetodoPago(id, nombre);
	            lista.add(metodo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

}
