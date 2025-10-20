package co.edu.unbosque.netflixbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.repository.UsuarioSuscripcionRepository;

@Service
public class UsuarioSuscripcionService {

	@Autowired
	private UsuarioSuscripcionRepository usuarioSuscripcionRepository;
	
	public int adquirirSuscripcion (int suscripcion, int usuario) {
		return usuarioSuscripcionRepository.adquirirSuscripcion(suscripcion, usuario);	
	}
	
}
