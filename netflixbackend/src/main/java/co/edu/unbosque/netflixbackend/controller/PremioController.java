package co.edu.unbosque.netflixbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.model.Premio;
import co.edu.unbosque.netflixbackend.service.PremioService;

@RestController
@RequestMapping("/premio")
@CrossOrigin(origins = "*")
public class PremioController {

    @Autowired
    private PremioService premioService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarPremio(@RequestBody Premio premio) {
        boolean exito = premioService.agregarPremio(premio);
        if (exito) {
            return new ResponseEntity<>("Premio agregado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al agregar premio", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Premio>> listarTodos() {
        List<Premio> lista = premioService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
