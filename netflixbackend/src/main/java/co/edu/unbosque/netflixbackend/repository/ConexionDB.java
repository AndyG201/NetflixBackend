package co.edu.unbosque.netflixbackend.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConexionDB { 
	
	private static final String URL = "jdbc:mysql://10.56.230.196:3306/DatabasesI";
	private static final String USUARIO = "anderson";
	private static final String CONTRASENIA = "Bases123+";
	
	public Connection obtenerConexion ()throws SQLException{
		return DriverManager.getConnection(URL,USUARIO,CONTRASENIA);
	}
	

}
