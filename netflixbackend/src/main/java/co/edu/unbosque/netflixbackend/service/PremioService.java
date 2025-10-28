package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.Premio;
import co.edu.unbosque.netflixbackend.repository.PremioRepository;

@Service
public class PremioService {

    @Autowired
    private PremioRepository premioRepository;


    public boolean agregarPremio(Premio premio) {
        if (premio.getNombre() == null || premio.getNombre().isEmpty()) {
            return false; 
        }
        return premioRepository.agregarPremio(premio);
    }
    
    public List<Premio> listarTodos() {
        return premioRepository.findAll();
    }
}
