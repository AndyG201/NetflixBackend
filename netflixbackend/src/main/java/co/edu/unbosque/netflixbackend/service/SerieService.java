package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Serie;
import co.edu.unbosque.netflixbackend.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;
	
	public List<Serie> obtenerSeries (){
		return serieRepository.obtenerTodasLasSeries();
	}
	
	public Serie buscarSeriePorId (int idSerie) {
		Serie found = serieRepository.obtenerSeriePorId(idSerie);
		if(found != null) {
			return found;
		}else {
			return null;
		}
	}
}

