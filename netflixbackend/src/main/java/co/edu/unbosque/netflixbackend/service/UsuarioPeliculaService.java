package co.edu.unbosque.netflixbackend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.UsuarioPelicula;
import co.edu.unbosque.netflixbackend.repository.UsuarioPeliculaRepository;

@Service
public class UsuarioPeliculaService {

	@Autowired
	private UsuarioPeliculaRepository usuarioPeliculaRepository;
	
	public boolean agregarPeliculaVistas (UsuarioPelicula usuarioPelicula) {
		return usuarioPeliculaRepository.agregarPeliculaVistas(usuarioPelicula);
	}
}
