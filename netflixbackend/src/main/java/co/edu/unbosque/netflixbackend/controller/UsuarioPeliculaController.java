package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.netflixbackend.model.UsuarioPelicula;
import co.edu.unbosque.netflixbackend.service.UsuarioPeliculaService;

@RestController
@RequestMapping("/usuariopelicula")
@CrossOrigin(origins = { "*" })
public class UsuarioPeliculaController {

	@Autowired
	private UsuarioPeliculaService usuarioPeliculaService;
	
	@PostMapping("/agregaralhistorial")
	public ResponseEntity<Boolean> agregarPeliculaVista(@RequestBody UsuarioPelicula usuarioPelicula) {
	    boolean agregado = usuarioPeliculaService.agregarPeliculaVistas(usuarioPelicula);

	    if (agregado) {
	        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
	    }
	}

}
