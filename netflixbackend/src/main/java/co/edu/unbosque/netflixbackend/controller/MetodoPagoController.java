package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.netflixbackend.dto.MetodoPagoDTO;
import co.edu.unbosque.netflixbackend.service.MetodoPagoService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/matodopago")
public class MetodoPagoController {

	@Autowired
	private MetodoPagoService metodoPagoService;
	
	@GetMapping("/obtenermetodosdepago")
	public ResponseEntity<List<MetodoPagoDTO>> obtenerMetodosPago (){
		List<MetodoPagoDTO> found = metodoPagoService.obtenerMetodosDePago();
		if(found != null) {
			return new ResponseEntity<List<MetodoPagoDTO>>(found, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<List<MetodoPagoDTO>>(found, HttpStatus.NOT_FOUND);
		}
	}
}
