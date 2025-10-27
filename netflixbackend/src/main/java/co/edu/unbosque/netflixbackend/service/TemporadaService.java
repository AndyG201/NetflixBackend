package co.edu.unbosque.netflixbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.TemporadaDTO;
import co.edu.unbosque.netflixbackend.model.Temporada;
import co.edu.unbosque.netflixbackend.repository.TemporadaRepository;

@Service
public class TemporadaService {
	
	@Autowired
	private TemporadaRepository temporadaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<TemporadaDTO> obtenerTemporadasPorSerie (int idSerie) {
		List<Temporada> found = temporadaRepository.obtenerTemporadasPorSerie(idSerie);
		List<TemporadaDTO> temporadas = new ArrayList<>();
		for (Temporada t : found) {
			temporadas.add(modelMapper.map(t, TemporadaDTO.class));
		}
		return temporadas;	
	}

}
