package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Episodio;

@Repository
public class EpisodioRepository {

	@Autowired
	private ConexionDB conexionDB = new ConexionDB();

	// INSERTAR un nuevo episodio
	public void insertarEpisodio(Episodio episodio) throws SQLException {
		String sql = "INSERT INTO episodio (titulo, numero_episodio, descripcion, poster, url_episodio, id_temporada) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, episodio.getTitulo());
			ps.setInt(2, episodio.getNumeroEpisodio());
			ps.setString(3, episodio.getDescripcion());
			ps.setString(4, episodio.getPoster());
			ps.setString(5, episodio.getUrlEpisodio());
			ps.setInt(6, episodio.getIdTemporada());

			ps.executeUpdate();
		}

	}

	// OBTENER todos los episodios
	public List<Episodio> obtenerTodosLosEpisodios() {
		List<Episodio> episodios = new ArrayList<>();
		String sql = "SELECT * FROM episodio";

		try (Connection conn = conexionDB.obtenerConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Episodio episodio = new Episodio();
				episodio.setIdEpisodio(rs.getInt("id_episodio"));
				episodio.setTitulo(rs.getString("titulo"));
				episodio.setNumeroEpisodio(rs.getInt("numero_episodio"));
				episodio.setDescripcion(rs.getString("descripcion"));
				episodio.setPoster(rs.getString("poster"));
				episodio.setUrlEpisodio(rs.getString("url_episodio"));
				episodio.setIdTemporada(rs.getInt("id_temporada"));

				episodios.add(episodio);
			}

		} catch (SQLException e) {
			System.err.println("❌ Error al obtener episodios: " + e.getMessage());
		}

		return episodios;
	}

	// OBTENER episodios por ID de temporada
	public List<Episodio> obtenerEpisodiosPorTemporada(int idTemporada) {
		List<Episodio> episodios = new ArrayList<>();
		String sql = "SELECT * FROM episodio WHERE id_temporada = ?";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idTemporada);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Episodio episodio = new Episodio();
					episodio.setIdEpisodio(rs.getInt("id_episodio"));
					episodio.setTitulo(rs.getString("titulo"));
					episodio.setNumeroEpisodio(rs.getInt("numero_episodio"));
					episodio.setDescripcion(rs.getString("descripcion"));
					episodio.setPoster(rs.getString("poster"));
					episodio.setUrlEpisodio(rs.getString("url_episodio"));
					episodio.setIdTemporada(rs.getInt("id_temporada"));

					episodios.add(episodio);
				}
			}

		} catch (SQLException e) {
			System.err.println("❌ Error al obtener episodios por temporada: " + e.getMessage());
		}

		return episodios;
	}

	// ACTUALIZAR un episodio
	public void actualizarEpisodio(Episodio episodio) {
		String sql = "UPDATE episodio SET titulo = ?, numero_episodio = ?, descripcion = ?, poster = ?, url_episodio = ?, id_temporada = ? WHERE id_episodio = ?";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, episodio.getTitulo());
			ps.setInt(2, episodio.getNumeroEpisodio());
			ps.setString(3, episodio.getDescripcion());
			ps.setString(4, episodio.getPoster());
			ps.setString(5, episodio.getUrlEpisodio());
			ps.setInt(6, episodio.getIdTemporada());
			ps.setInt(7, episodio.getIdEpisodio());

			ps.executeUpdate();
			System.out.println("✅ Episodio actualizado correctamente: " + episodio.getTitulo());

		} catch (SQLException e) {
			System.err.println("❌ Error al actualizar episodio: " + e.getMessage());
		}
	}

	// ELIMINAR episodio por ID
	public void eliminarEpisodio(int idEpisodio) {
		String sql = "DELETE FROM episodio WHERE id_episodio = ?";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idEpisodio);
			ps.executeUpdate();
			System.out.println("🗑️ Episodio eliminado con ID: " + idEpisodio);

		} catch (SQLException e) {
			System.err.println("❌ Error al eliminar episodio: " + e.getMessage());
		}
	}

	// OBTENER un episodio por ID
	public Episodio obtenerEpisodioPorId(int idEpisodio) {
		Episodio episodio = null;
		String sql = "SELECT * FROM episodio WHERE id_episodio = ?";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idEpisodio);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					episodio = new Episodio();
					episodio.setIdEpisodio(rs.getInt("id_episodio"));
					episodio.setTitulo(rs.getString("titulo"));
					episodio.setNumeroEpisodio(rs.getInt("numero_episodio"));
					episodio.setDescripcion(rs.getString("descripcion"));
					episodio.setPoster(rs.getString("poster"));
					episodio.setUrlEpisodio(rs.getString("url_episodio"));
					episodio.setIdTemporada(rs.getInt("id_temporada"));
				}
			}

		} catch (SQLException e) {
			System.err.println("❌ Error al obtener episodio: " + e.getMessage());
		}

		return episodio;
	}
}
