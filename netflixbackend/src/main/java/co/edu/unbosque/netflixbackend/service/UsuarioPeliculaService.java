package co.edu.unbosque.netflixbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.UsuarioPeliculaDTO;
import co.edu.unbosque.netflixbackend.model.UsuarioPelicula;
import co.edu.unbosque.netflixbackend.repository.UsuarioPeliculaRepository;

@Service
public class UsuarioPeliculaService {

	@Autowired
	private UsuarioPeliculaRepository usuarioPeliculaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public boolean agregarPeliculaVistas (UsuarioPeliculaDTO usuarioPeliculaDTO) {
		return usuarioPeliculaRepository.agregarPeliculaVistas(modelMapper.map(usuarioPeliculaDTO, UsuarioPelicula.class));
	}
}
