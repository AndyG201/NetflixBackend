package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.unbosque.netflixbackend.service.UsuarioSuscripcionService;

@Controller
@RequestMapping("/usuariosuscripcion")
@CrossOrigin(origins = { "*" })
public class UsuarioSuscripcionController {

	@Autowired
	private UsuarioSuscripcionService usuarioSuscripcionService;
	
	@PostMapping("/adquirirsuscripcion")
	public ResponseEntity<String> adquirirSuscipcion (int suscripcion, int usuario){
		int rta = usuarioSuscripcionService.adquirirSuscripcion(suscripcion, usuario);
		if (rta == 1) {
			return new ResponseEntity<String>("Suscripción Adquirida",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Suscripción no Adquirida",HttpStatus.BAD_REQUEST);
		}
	}
}
