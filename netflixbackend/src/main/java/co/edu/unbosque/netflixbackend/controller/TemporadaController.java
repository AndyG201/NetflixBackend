package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.dto.TemporadaDTO;
import co.edu.unbosque.netflixbackend.service.TemporadaService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/temporada")
public class TemporadaController {

    @Autowired
    private TemporadaService temporadaService;

    @GetMapping("/buscartemporadaporidserie")
    public List<TemporadaDTO> obtenerTemporadasPorSerie(@RequestParam int idSerie) {
        return temporadaService.obtenerTemporadasPorSerie(idSerie);
    }
}
