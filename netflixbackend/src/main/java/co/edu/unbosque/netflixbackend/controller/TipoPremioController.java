package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.model.TipoPremio;
import co.edu.unbosque.netflixbackend.service.TipoPremioService;

@RestController
@RequestMapping("/tipopremio")
@CrossOrigin(origins = "*")
public class TipoPremioController {

    @Autowired
    private TipoPremioService tipoPremioService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarTipo(@RequestBody TipoPremio tipo) {
        boolean exito = tipoPremioService.agregarTipoPremio(tipo);
        if (exito) {
            return new ResponseEntity<>("Tipo de premio agregado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al agregar tipo", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoPremio>> listarTodos() {
        List<TipoPremio> lista = tipoPremioService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoPremio> buscarPorId(@RequestParam int id) {
        TipoPremio tipo = tipoPremioService.buscarPorId(id);
        if (tipo != null) {
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
