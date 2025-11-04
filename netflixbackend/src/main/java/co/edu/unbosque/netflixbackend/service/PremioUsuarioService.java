package co.edu.unbosque.netflixbackend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.PremioUsuario;
import co.edu.unbosque.netflixbackend.repository.PremioRepository;
import co.edu.unbosque.netflixbackend.repository.PremioUsuarioRepository;

@Service
public class PremioUsuarioService {

    @Autowired
    private PremioUsuarioRepository premioUsuarioRepository;

    @Autowired
    private PremioRepository premioRepository; // ✅ AGREGADO

    public List<PremioUsuario> obtenerPremiosPorUsuario(int idUsuario) {
        return premioUsuarioRepository.obtenerPremiosPorUsuario(idUsuario);
    }

    public boolean tienePremio(int idUsuario, int idPremio) {
        return premioUsuarioRepository.tienePremio(idUsuario, idPremio);
    }

    public boolean asignarPremio(int idUsuario, int idPremio) {
        System.out.println("🎁 Asignando premio " + idPremio + " al usuario " + idUsuario);
        return premioUsuarioRepository.asignarPremio(idUsuario, idPremio);
    }

    // ✅ Devuelve la info reducida (solo nombre + recompensa)
    public List<Map<String, String>> obtenerPremiosConInfo(int idUsuario) {
        List<PremioUsuario> premiosRaw = premioUsuarioRepository.obtenerPremiosPorUsuario(idUsuario);
        List<Map<String, String>> listaFinal = new ArrayList<>();

        for (PremioUsuario p : premiosRaw) {
            Map<String, String> info = premioRepository.obtenerNombreYRecompensa(p.getIdPremio());
            if (!info.isEmpty()) {
                listaFinal.add(info);
            }
        }

        return listaFinal;
    }
}
