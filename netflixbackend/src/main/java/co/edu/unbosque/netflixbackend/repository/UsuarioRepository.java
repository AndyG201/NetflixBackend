package co.edu.unbosque.netflixbackend.repository;
import java.sql.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Usuario;

@Repository
public class UsuarioRepository {
	
	@Autowired
	private ConexionDB conexionDB;
	
	
	public boolean crearUsuario(Usuario usuario) {
	    String sql = "INSERT INTO usuario (primer_nombre, primer_apellido, correo, telefono, fecha_nacimiento, contrasenia, fecha_registro, id_estado)"
	               + "VALUES (?, ?, ?, ?, ?, ?,?,?)";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, usuario.getPrimerNombre());
	        ps.setString(2, usuario.getPrimerApellido());
	        ps.setString(3, usuario.getCorreo());
	        ps.setString(4, usuario.getTelefono());
	        ps.setTimestamp(5, Timestamp.valueOf(usuario.getFechaNacimiento().atStartOfDay()));
	        ps.setString(6, usuario.getContrasenia());
	        ps.setTimestamp(7, Timestamp.valueOf(usuario.getFechaRegistro()));
	        ps.setInt(8, usuario.getIdEstado());


	        int filasInsertadas = ps.executeUpdate();

	        if (filasInsertadas > 0) {
	            return true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}


	
	public boolean eliminarUsuario(int idUsuario) {
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
	                usuario.setIdUsuario(rs.getInt("id_usuario"));
	                usuario.setPrimerNombre(rs.getString("primer_nombre"));
	                usuario.setPrimerApellido(rs.getString("primer_apellido"));
	                usuario.setCorreo(rs.getString("correo"));
	                usuario.setTelefono(rs.getString("telefono"));

	                Timestamp fecha = rs.getTimestamp("fecha_nacimiento");
	                if (fecha != null) {
	                    usuario.setFechaNacimiento(fecha.toLocalDateTime().toLocalDate());
	                }

	                usuario.setContrasenia(rs.getString("contrasenia"));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuario; 
	}

	
	public Usuario findById(int  id) {
	    Usuario usuario = null;
	    String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

	    try (Connection conn = conexionDB.obtenerConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, id);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                usuario = new Usuario();
	                usuario.setIdUsuario(rs.getInt("id_usuario"));
	                usuario.setPrimerNombre(rs.getString("primer_nombre"));
	                usuario.setPrimerApellido(rs.getString("primer_apellido"));
	                usuario.setCorreo(rs.getString("correo"));
	                usuario.setTelefono(rs.getString("telefono"));

	                Timestamp fecha = rs.getTimestamp("fecha_nacimiento");
	                if (fecha != null) {
	                    usuario.setFechaNacimiento(fecha.toLocalDateTime().toLocalDate());
	                }

	                usuario.setContrasenia(rs.getString("contrasenia"));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuario; 
	}
	
	public Usuario login(String correo, String contrasenia) {
		Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";
        try (Connection conn = conexionDB.obtenerConexion();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasenia); 

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setPrimerNombre(rs.getString("primer_nombre"));
                usuario.setPrimerApellido(rs.getString("primer_apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));

                Timestamp fecha = rs.getTimestamp("fecha_nacimiento");
                if (fecha != null) {
                    usuario.setFechaNacimiento(fecha.toLocalDateTime().toLocalDate());
                }

                usuario.setContrasenia(rs.getString("contrasenia"));
                return usuario;
            } else {
                return null; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	

}
