package co.edu.unbosque.netflixbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.SuscripcionDTO;
import co.edu.unbosque.netflixbackend.model.Suscripcion;
import co.edu.unbosque.netflixbackend.repository.SuscripcionRepository;

@Service
public class SuscripcionService {
	
	@Autowired
	private	SuscripcionRepository suscripcionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<SuscripcionDTO> obtenerSuscripciones (){
		List<SuscripcionDTO> suscripciones = new ArrayList<>();
		List<Suscripcion> encontradas = suscripcionRepository.getAll();
		for (Suscripcion s : encontradas) {
		
			suscripciones.add(modelMapper.map(s, SuscripcionDTO.class));
		}
		return suscripciones;	
	}

}
