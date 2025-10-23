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

import co.edu.unbosque.netflixbackend.dto.DocumentalDTO;
import co.edu.unbosque.netflixbackend.model.Documental;
import co.edu.unbosque.netflixbackend.service.DocumentalService;

@RestController
@RequestMapping("/documental")
@CrossOrigin(origins = { "*" })
public class DocumentalController {

    @Autowired
    private DocumentalService documentalService;

    @GetMapping("/obtenerdocumentalpornombre")
    public ResponseEntity<DocumentalDTO> obtenerDocumentalPorNombre(@RequestParam String nombre) {
        DocumentalDTO encontrado = documentalService.buscarPorNombre(nombre.toLowerCase());
        if (encontrado != null) {
            return new ResponseEntity<>(encontrado, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/obtenerdocumentalespopulares")
    public ResponseEntity<List<Documental>> obtenerDocumentalesPorPopularidad(@RequestParam double minPopularidad) {
        List<Documental> documentales = documentalService.buscarPorPopularidad(minPopularidad);
        if (documentales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(documentales, HttpStatus.OK);
        }
    }

    @GetMapping("/obtenertodosdocumentales")
    public ResponseEntity<List<Documental>> obtenerTodosLosDocumentales() {
        List<Documental> documentales = documentalService.buscarTodos();
        if (documentales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(documentales, HttpStatus.OK);
        }
    }
}
