package co.edu.unbosque.netflixbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.PuntosUsuario;
import co.edu.unbosque.netflixbackend.repository.PuntosUsuarioRepository;

@Service
public class PuntosUsuarioService {

    @Autowired
    private PuntosUsuarioRepository puntosUsuarioRepository;

    @Autowired
    private PremioUsuarioService premioUsuarioService;

    // ✅ Sumar puntos de manera dinámica
    public boolean sumarPuntos(int idUsuario, int puntos) {
        PuntosUsuario p = puntosUsuarioRepository.obtenerPorIdUsuario(idUsuario);

        // Si no existe, crearlo
        if (p == null) {
            boolean creado = puntosUsuarioRepository.crearRegistroPuntos(idUsuario);
            if (!creado) {
                System.err.println("❌ No se pudo crear el registro de puntos para el usuario " + idUsuario);
                return false;
            }
        }

        boolean actualizado = puntosUsuarioRepository.sumarPuntos(idUsuario, puntos);

        if (actualizado) {
            // ✅ Después de sumar, verificar si alcanza premios
            verificarYAsignarPremio(idUsuario);
        }

        return actualizado;
    }

    // ✅ Consultar puntos del usuario
    public PuntosUsuario obtenerPuntosUsuario(int idUsuario) {
        return puntosUsuarioRepository.obtenerPorIdUsuario(idUsuario);
    }

    // 🎁 Verificar si el usuario alcanza premios según puntos actuales
    public void verificarYAsignarPremio(int idUsuario) {
        PuntosUsuario puntosUsuario = puntosUsuarioRepository.obtenerPorIdUsuario(idUsuario);
        if (puntosUsuario == null) return;

        int puntos = puntosUsuario.getPuntosTotales();

        // Premios basados en la tabla "premio"
        if (puntos >= 50 && !premioUsuarioService.tienePremio(idUsuario, 1)) {
            premioUsuarioService.asignarPremio(idUsuario, 1);
        }
        if (puntos >= 100 && !premioUsuarioService.tienePremio(idUsuario, 2)) {
            premioUsuarioService.asignarPremio(idUsuario, 2);
        }
        if (puntos >= 150 && !premioUsuarioService.tienePremio(idUsuario, 3)) {
            premioUsuarioService.asignarPremio(idUsuario, 3);
        }
    }
}
