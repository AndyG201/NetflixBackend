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

import co.edu.unbosque.netflixbackend.dto.SerieDTO;
import co.edu.unbosque.netflixbackend.service.SerieService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/serie")

public class SerieController {

	@Autowired
	private SerieService serieService;
	
	@GetMapping("obtenertodaslasseries")
	public ResponseEntity<List<SerieDTO>> buscarTodasLasSeries (){
		List<SerieDTO> found = serieService.obtenerSeries();
		if(found != null) {
			return new ResponseEntity<List<SerieDTO>>(found, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<List<SerieDTO>>(found, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("obtenerserieporid")
	public ResponseEntity<SerieDTO> buscarSeriePorId (@RequestParam int idSerie ){
		SerieDTO found = serieService.buscarSeriePorId(idSerie);
		if(found != null) {
			return new ResponseEntity<SerieDTO>(found , HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(found, HttpStatus.NOT_FOUND);
		}
	}
}
