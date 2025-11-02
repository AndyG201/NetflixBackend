package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.service.UsuarioSuscripcionService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioSuscripcionController {

    @Autowired
    private UsuarioSuscripcionService usuarioSuscripcionService;

    @PostMapping("/suscribirse")
    public ResponseEntity<String> suscribirse(
            @RequestParam int idUsuario,
            @RequestParam int idSuscripcion) {
        try {
            int filas = usuarioSuscripcionService.adquirirSuscripcion(idSuscripcion, idUsuario);
            if (filas > 0) {
                return ResponseEntity.ok("Suscripción realizada con éxito");
            } else {
                return ResponseEntity.badRequest().body("No se pudo realizar la suscripción");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error interno al realizar la suscripción");
        }
    }
}
