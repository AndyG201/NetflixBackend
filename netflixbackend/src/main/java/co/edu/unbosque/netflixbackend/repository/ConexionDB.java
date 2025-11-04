package co.edu.unbosque.netflixbackend.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;


@Component
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/databasesi?useSSL=false&serverTimezone=UTC";
	private static final String USUARIO = "federico";
	private static final String CONTRASENIA = "Bases123+";
	
	public Connection obtenerConexion ()throws SQLException{
		return DriverManager.getConnection(URL,USUARIO,CONTRASENIA);
	}
    // 🔹 Método main para probar la conexión manualmente
    public static void main(String[] args) {
        ConexionDB conexion = new ConexionDB();
        try (Connection conn = conexion.obtenerConexion()) {
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
    }

}
