package co.edu.unbosque.netflixbackend.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.netflixbackend.model.Premio;

@Repository
public class PremioRepository {

    @Autowired
    private ConexionDB conexionDB;

    public boolean agregarPremio(Premio premio) {
        String sql = "INSERT INTO premio (nombre, descripcion, id_tipo_premio, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, premio.getNombre());
            ps.setString(2, premio.getDescripcion());
            ps.setInt(3, premio.getTipoPremio());
            ps.setString(4, premio.getFechaInicio());
            ps.setString(5, premio.getFechaFin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Premio findById(int idPremio) {
        Premio premio = null;
        String sql = "SELECT * FROM premio WHERE id_premio = ?";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPremio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    premio = new Premio();
                    premio.setIdPremio(rs.getInt("id_premio"));
                    premio.setNombre(rs.getString("nombre"));
                    premio.setDescripcion(rs.getString("descripcion"));
                    premio.setTipoPremio(rs.getInt("id_tipo_premio"));
                    premio.setFechaInicio(rs.getString("fecha_inicio"));
                    premio.setFechaFin(rs.getString("fecha_fin"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return premio;
    }

    public List<Premio> findAll() {
        List<Premio> lista = new ArrayList<>();
        String sql = "SELECT * FROM premio";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Premio p = new Premio();
                p.setIdPremio(rs.getInt("id_premio"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setTipoPremio(rs.getInt("id_tipo_premio"));
                p.setFechaInicio(rs.getString("fecha_inicio"));
                p.setFechaFin(rs.getString("fecha_fin"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
