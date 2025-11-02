package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.netflixbackend.model.Suscripcion;
import co.edu.unbosque.netflixbackend.service.SuscripcionService;

@RestController
@RequestMapping("/suscripciones")
@CrossOrigin(origins = { "*" })
public class SuscripcionController {

	@Autowired
	private SuscripcionService suscripcionService;

	@GetMapping
	public ResponseEntity<List<Suscripcion>> obtenerSuscripciones() {
	    List<Suscripcion> suscripciones = suscripcionService.obtenerSuscripciones();

	    if (suscripciones == null || suscripciones.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(suscripciones, HttpStatus.OK);
	    }
	}

}
