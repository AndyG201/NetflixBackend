package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.netflixbackend.dto.PagoDTO;
import co.edu.unbosque.netflixbackend.service.GeneradorPdfService;
import co.edu.unbosque.netflixbackend.service.PagoService;

@RestController
@RequestMapping("/pago")
@CrossOrigin(origins = { "*" })
public class PagoController {
	
	@Autowired
	private PagoService pagoService;
	
	@Autowired
	private GeneradorPdfService generadorPdfService;
	
	@PostMapping
	public ResponseEntity<String> crearPago (@RequestBody PagoDTO pagoDTO){
		boolean rta = pagoService.crearPago(pagoDTO);
		if(rta) {
			return new ResponseEntity<String>("Pago creado", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Pago no creado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<String> enviarPdf (@RequestParam String referencia){
		return new ResponseEntity<String>(generadorPdfService.crearPdf(referencia),HttpStatus.ACCEPTED);
	}

}
