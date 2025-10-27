package co.edu.unbosque.netflixbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.MetodoPagoDTO;
import co.edu.unbosque.netflixbackend.model.MetodoPago;
import co.edu.unbosque.netflixbackend.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

	@Autowired
	private MetodoPagoRepository metodoPagoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<MetodoPagoDTO> obtenerMetodosDePago (){
		List<MetodoPagoDTO> metodos = new ArrayList<>();
		List<MetodoPago> found = metodoPagoRepository.obtenerMetodosDePago();
		for (MetodoPago m : found) {
			metodos.add(modelMapper.map(m, MetodoPagoDTO.class));
		}
		return metodos;
	}
}
