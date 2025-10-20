package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.netflixbackend.dto.SuscripcionDTO;
import co.edu.unbosque.netflixbackend.service.SuscripcionService;

@RestController
@RequestMapping("/suscripcion")
@CrossOrigin(origins = { "*" })
public class SuscripcionController {

	@Autowired
	private SuscripcionService suscripcionService;
	
	@GetMapping("/obtenersuscripciones")
	public ResponseEntity<List<SuscripcionDTO>> obtenerSuscripciones(){
		return new ResponseEntity<List<SuscripcionDTO>>(suscripcionService.obtenerSuscripciones(),HttpStatus.ACCEPTED);
	}
}
