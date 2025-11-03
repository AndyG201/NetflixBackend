package co.edu.unbosque.netflixbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.PuntosUsuario;
import co.edu.unbosque.netflixbackend.repository.PuntosUsuarioRepository;

@Service
public class PuntosUsuarioService {

    @Autowired
    private PuntosUsuarioRepository puntosUsuarioRepository;

    // ✅ Sumar puntos de manera dinámica
    public boolean sumarPuntos(int idUsuario, int puntos) {
        PuntosUsuario p = puntosUsuarioRepository.obtenerPorIdUsuario(idUsuario);

        if (p == null) {
            boolean creado = puntosUsuarioRepository.crearRegistroPuntos(idUsuario);
            if (!creado) return false; // Si falla la creación, salimos
        }

        return puntosUsuarioRepository.sumarPuntos(idUsuario, puntos);
    }

    // Obtener puntos del usuario
    public PuntosUsuario obtenerPuntosUsuario(int idUsuario) {
        return puntosUsuarioRepository.obtenerPorIdUsuario(idUsuario);
    }
}
