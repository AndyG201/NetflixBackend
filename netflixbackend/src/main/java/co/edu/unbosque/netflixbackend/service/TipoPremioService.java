package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.TipoPremio;
import co.edu.unbosque.netflixbackend.repository.TipoPremioRepository;

@Service
public class TipoPremioService {

    @Autowired
    private TipoPremioRepository tipoPremioRepository;

    public boolean agregarTipoPremio(TipoPremio tipo) {
        if (tipo.getNombreTipo() == null || tipo.getNombreTipo().isEmpty()) {
            return false;
        }
        return tipoPremioRepository.agregarTipoPremio(tipo);
    }

    public TipoPremio buscarPorId(int id) {
        return tipoPremioRepository.findById(id);
    }

    public List<TipoPremio> listarTodos() {
        return tipoPremioRepository.findAll();
    }

}
