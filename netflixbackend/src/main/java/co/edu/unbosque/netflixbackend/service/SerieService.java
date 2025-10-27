package co.edu.unbosque.netflixbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.SerieDTO;
import co.edu.unbosque.netflixbackend.model.Serie;
import co.edu.unbosque.netflixbackend.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<SerieDTO> obtenerSeries (){
		List<SerieDTO> series = new ArrayList<>();
		List<Serie> seriesEncontradas = serieRepository.obtenerTodasLasSeries();
		for (Serie s : seriesEncontradas) {
			series.add(modelMapper.map(s, SerieDTO.class));
		}
		return series;
	}
	
	public SerieDTO buscarSeriePorId (int idSerie) {
		Serie found = serieRepository.obtenerSeriePorId(idSerie);
		if(found != null) {
			return modelMapper.map(found, SerieDTO.class);
		}else {
			return null;
		}
	}
}

