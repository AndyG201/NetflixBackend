package co.edu.unbosque.netflixbackend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.model.Episodio;
import co.edu.unbosque.netflixbackend.service.EpisodioService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/episodio")
public class EpisodioController {

    @Autowired
    private EpisodioService episodioService;

    @GetMapping("/buscartodoslos")
    public ResponseEntity<List<Episodio>> obtenerTodosLosEpisodios() {
        List<Episodio> episodios = episodioService.obtenerTodosLosEpisodios();
        if (episodios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(episodios, HttpStatus.OK);
    }

    @GetMapping("/todoslosepisodiosportemporada")
    public ResponseEntity<List<Episodio>> obtenerEpisodiosPorTemporada(@PathVariable int idTemporada) {
        List<Episodio> episodios = episodioService.obtenerEpisodiosPorTemporada(idTemporada);
        if (episodios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(episodios, HttpStatus.OK);
    }

    @GetMapping("/buscarepisodioporid")
    public ResponseEntity<Episodio> obtenerEpisodioPorId(@PathVariable int idEpisodio) {
        Episodio episodio = episodioService.obtenerEpisodioPorId(idEpisodio);
        if (episodio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(episodio, HttpStatus.OK);
    }
}
