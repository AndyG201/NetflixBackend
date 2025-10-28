package co.edu.unbosque.netflixbackend.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;


@Component
public class ConexionDB { 
	
	private static final String URL = "jdbc:mysql://100.94.64.29/databasesi";
	private static final String USUARIO = "federico";
	private static final String CONTRASENIA = "Bases123+";
	
	public Connection obtenerConexion ()throws SQLException{
		return DriverManager.getConnection(URL,USUARIO,CONTRASENIA);
	}
	

}
