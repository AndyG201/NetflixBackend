package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.Episodio;
import co.edu.unbosque.netflixbackend.repository.EpisodioRepository;

@Service
public class EpisodioService {

    @Autowired
    private EpisodioRepository episodioRepository;

    public List<Episodio> obtenerTodosLosEpisodios() {
        return episodioRepository.obtenerTodosLosEpisodios();
    }

    public List<Episodio> obtenerEpisodiosPorTemporada(int idTemporada) {
        return episodioRepository.obtenerEpisodiosPorTemporada(idTemporada);
    }

    public Episodio obtenerEpisodioPorId(int idEpisodio) {
        return episodioRepository.obtenerEpisodioPorId(idEpisodio);
    }
}
