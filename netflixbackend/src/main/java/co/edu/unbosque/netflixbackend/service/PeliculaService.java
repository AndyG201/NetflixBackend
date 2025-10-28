package co.edu.unbosque.netflixbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Pelicula;
import co.edu.unbosque.netflixbackend.repository.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepository;


	public Pelicula buscarPorNombre(String nombre) {
		Pelicula encontrada = peliculaRepository.findByNombre(nombre);
		if (encontrada != null) {
			return encontrada;
		} else {
			return null;
		}
	}

	public List<Pelicula> buscarTodas() {
		return peliculaRepository.findAll();
	}

	public List<Pelicula> buscarPorPopularidad(double maxPopularidad) {
		return peliculaRepository.findByPopularidad(maxPopularidad);
	}
}
