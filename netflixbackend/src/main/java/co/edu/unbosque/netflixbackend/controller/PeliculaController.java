package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.netflixbackend.model.Pelicula;
import co.edu.unbosque.netflixbackend.service.PeliculaService;

@RestController
@RequestMapping("/pelicula")
@CrossOrigin(origins = { "*" })
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	
	@GetMapping("obtenerpeliculapornombre")
	public ResponseEntity<Pelicula> obtenerPeliculaPorNombre (@RequestParam String nombre){
		Pelicula encontrada = peliculaService.buscarPorNombre(nombre.toLowerCase());
		if(encontrada != null) {
			return new ResponseEntity<Pelicula>(encontrada, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Pelicula>(encontrada, HttpStatus.NO_CONTENT);
		}
	}
	
	 @GetMapping("/obtenerpeliculaspopulares")
	    public ResponseEntity<List<Pelicula>> obtenerPeliculasPorPopularidad(@RequestParam double minPopularidad) {
	        List<Pelicula> peliculas = peliculaService.buscarPorPopularidad(minPopularidad);
	        if (peliculas.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(peliculas, HttpStatus.OK);
	        }
	    }

	    @GetMapping("/obtenertodaspeliculas")
	    public ResponseEntity<List<Pelicula>> obtenerTodasLasPeliculas() {
	        List<Pelicula> peliculas = peliculaService.buscarTodas();
	        if (peliculas.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(peliculas, HttpStatus.OK);
	        }
	    }
}
