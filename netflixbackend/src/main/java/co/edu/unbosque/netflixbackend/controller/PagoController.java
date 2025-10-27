package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.dto.PagoDTO;
import co.edu.unbosque.netflixbackend.service.PagoService;

@RestController
@RequestMapping("/pago")
@CrossOrigin(origins = { "*" })
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody PagoDTO pagoDTO) {
        try {
            String referencia = pagoService.crearPago(pagoDTO);
            return new ResponseEntity<>(referencia, HttpStatus.CREATED); // devuelve la referencia al frontend
        } catch (RuntimeException e) {
            // error controlado (por ejemplo: suscripcion no encontrada)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creando pago");
        }
    }

    @GetMapping
    public ResponseEntity<String> enviarPdf(@RequestParam String referencia) {
        // Si quieres mantener GET separado, puedes
        return new ResponseEntity<String>("Use POST /pago to create and send pdf", HttpStatus.OK);
    }
}
