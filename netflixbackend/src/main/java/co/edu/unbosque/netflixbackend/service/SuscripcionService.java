package co.edu.unbosque.netflixbackend.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Suscripcion;
import co.edu.unbosque.netflixbackend.repository.SuscripcionRepository;

@Service
public class SuscripcionService {
	
	@Autowired
	private	SuscripcionRepository suscripcionRepository;
	
	public List<Suscripcion> obtenerSuscripciones (){
		return suscripcionRepository.getAll();
	}

}
