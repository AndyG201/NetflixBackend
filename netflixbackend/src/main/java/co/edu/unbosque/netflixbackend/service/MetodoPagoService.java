package co.edu.unbosque.netflixbackend.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.MetodoPago;
import co.edu.unbosque.netflixbackend.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

	@Autowired
	private MetodoPagoRepository metodoPagoRepository;
	
	public List<MetodoPago> obtenerMetodosDePago (){
		return metodoPagoRepository.obtenerMetodosDePago();
	}
}
