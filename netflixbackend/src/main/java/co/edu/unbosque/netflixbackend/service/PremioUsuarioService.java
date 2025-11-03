package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.PremioUsuario;
import co.edu.unbosque.netflixbackend.repository.PremioUsuarioRepository;

@Service
public class PremioUsuarioService {

    @Autowired
    private PremioUsuarioRepository premioUsuarioRepository;

    public List<PremioUsuario> obtenerPremiosPorUsuario(int idUsuario) {
        return premioUsuarioRepository.obtenerPremiosPorUsuario(idUsuario);
    }
}
