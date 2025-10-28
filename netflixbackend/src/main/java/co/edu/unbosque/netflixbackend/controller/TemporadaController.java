package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.netflixbackend.model.Temporada;
import co.edu.unbosque.netflixbackend.service.TemporadaService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/temporada")
public class TemporadaController {

    @Autowired
    private TemporadaService temporadaService;

    @GetMapping("/buscartemporadaporidserie")
    public ResponseEntity<List<Temporada>> obtenerTemporadasPorSerie(@RequestParam int idSerie) {
        List<Temporada> temporadas = temporadaService.obtenerTemporadasPorSerie(idSerie);

        if (temporadas == null || temporadas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 si no hay temporadas
        } else {
            return new ResponseEntity<>(temporadas, HttpStatus.OK); // 200 si las encontró
        }
    }

}
