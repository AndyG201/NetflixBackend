package co.edu.unbosque.netflixbackend.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Temporada;
import co.edu.unbosque.netflixbackend.repository.TemporadaRepository;

@Service
public class TemporadaService {
	
	@Autowired
	private TemporadaRepository temporadaRepository;
	
	public List<Temporada> obtenerTemporadasPorSerie (int idSerie) {
		return temporadaRepository.obtenerTodasLasTemporadas();	
	}

}
