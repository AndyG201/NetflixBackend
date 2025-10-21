package co.edu.unbosque.netflixbackend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Pago;

@Repository
public class PagoRepository {

	@Autowired
	private ConexionDB conexionDB = new ConexionDB();

	public boolean crearPago(Pago pago) {
		String sql = "INSERT INTO pago (fecha_maxima, referencia, monto, id_usuario, id_suscripcion, id_estado_pago, id_metodo_pago) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setTimestamp(1, Timestamp.valueOf(pago.getFecha().atStartOfDay()));
			ps.setString(2, pago.getReferencia());
			ps.setInt(3, pago.getMonto());
			ps.setInt(4, pago.getIdUsuario());
			ps.setInt(5, pago.getIdSuscripcion());
			ps.setInt(6, pago.getIdEstadoPago());
			ps.setInt(7, pago.getIdMetodoPago());

			int filasInsertadas = ps.executeUpdate();
			return filasInsertadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Pago buscarPorReferencia(String referencia) {
		Pago pago = null;
		String sql = "SELECT * FROM pago WHERE referencia = ?";

		try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, referencia);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					pago = new Pago();
					pago.setIdPago(rs.getInt("id_pago"));
					pago.setFecha(rs.getTimestamp("fecha_maxima").toLocalDateTime().toLocalDate());
					pago.setReferencia(rs.getString("referencia"));
					pago.setMonto(rs.getInt("monto"));
					pago.setIdUsuario(rs.getInt("id_usuario"));
					pago.setIdSuscripcion(rs.getInt("id_suscripcion"));
					pago.setIdEstadoPago(rs.getInt("id_estado_pago"));
					pago.setIdMetodoPago(rs.getInt("id_metodo_pago"));
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al buscar el pago con referencia '" + referencia + "': " + e.getMessage());
		}

		return pago;
	}

}
