package co.edu.unbosque.netflixbackend.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Usuario;

@Repository
public class UsuarioRepository {
	
	@Autowired
	private static ConexionDB conexionDB = new ConexionDB();
	
	
	public boolean crearUsuario(Usuario usuario) {
	    String sql = "INSERT INTO usuario (primerNombre, primerApellido, correo, telefono, fechaNacimiento, contrasenia) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, usuario.getPrimerNombre());
	        ps.setString(2, usuario.getPrimerApellido());
	        ps.setString(3, usuario.getCorreo());
	        ps.setString(4, usuario.getTelefono());
	        ps.setTimestamp(5, Timestamp.valueOf(usuario.getFechaNacimiento())); 
	        ps.setString(6, usuario.getContrasenia());

	        int filasInsertadas = ps.executeUpdate();

	        if (filasInsertadas > 0) {
	            return true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}


	
	public static boolean eliminarUsuario(int idUsuario) {
		String sql = "DELETE FROM usuario WHERE idUsuario = ?";
		try (Connection conn = conexionDB.obtenerConexion();
		PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, idUsuario);
			int filasEliminadas = ps.executeUpdate();
			if (filasEliminadas > 0) {
	            return true;
	        }
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public Usuario findByEmail(String email) {
	    Usuario usuario = null;
	    String sql = "SELECT * FROM usuario WHERE correo = ?";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                usuario = new Usuario();
	                usuario.setIdUsuario(rs.getInt("idUsuario"));
	                usuario.setPrimerNombre(rs.getString("primerNombre"));
	                usuario.setPrimerApellido(rs.getString("primerApellido"));
	                usuario.setCorreo(rs.getString("correo"));
	                usuario.setTelefono(rs.getString("telefono"));

	                Timestamp fecha = rs.getTimestamp("fechaNacimiento");
	                if (fecha != null) {
	                    usuario.setFechaNacimiento(fecha.toLocalDateTime());
	                }

	                usuario.setContrasenia(rs.getString("contrasenia"));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuario; 
	}

	public List<Usuario> findAllUsers () throws SQLException{
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		
		try(Connection conn = conexionDB.obtenerConexion();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			){
				while(rs.next()) {
					
					String nombre =rs.getString("primerNombre");
					String apellido = rs.getString("primerApellido");
					Usuario u = new Usuario();
					usuarios.add(u);
				}
		}
		return usuarios;
	}
	
	

}
