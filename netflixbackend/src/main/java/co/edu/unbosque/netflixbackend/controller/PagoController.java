package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.netflixbackend.model.Pago;
import co.edu.unbosque.netflixbackend.service.PagoService;

@RestController
@RequestMapping("/pago")
@CrossOrigin(origins = { "*" })
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody Pago pagoDTO) {
        try {
            String referencia = pagoService.crearPago(pagoDTO);
            return new ResponseEntity<>(referencia, HttpStatus.CREATED); 
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creando pago");
        }
    }

    @GetMapping
    public ResponseEntity<String> enviarPdf(@RequestParam String referencia) {
        return new ResponseEntity<String>("Use POST /pago to create and send pdf", HttpStatus.OK);
    }
}
