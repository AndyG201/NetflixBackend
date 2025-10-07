package co.edu.unbosque.netflixbackend.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB { 
	
	private static final String URL = "jdbc:mysql://10.79.158.196:3306/DatabasesI";
	private static final String USUARIO = "anderson";
	private static final String CONTRASENIA = "Bases123+";
	
	public Connection obtenerConexion ()throws SQLException{
		return DriverManager.getConnection(URL,USUARIO,CONTRASENIA);
	}
	

}
