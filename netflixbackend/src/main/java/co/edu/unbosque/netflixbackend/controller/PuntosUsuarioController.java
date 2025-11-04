package co.edu.unbosque.netflixbackend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.model.PuntosUsuario;
import co.edu.unbosque.netflixbackend.service.PuntosUsuarioService;
import co.edu.unbosque.netflixbackend.service.PremioUsuarioService;

@RestController
@RequestMapping("/puntos")
@CrossOrigin(origins = "*")
public class PuntosUsuarioController {

    @Autowired
    private PuntosUsuarioService puntosUsuarioService;

    @Autowired
    private PremioUsuarioService premioUsuarioService;

    @PostMapping("/sumar/{idUsuario}")
    public ResponseEntity<String> sumarPuntos(
            @PathVariable int idUsuario,
            @RequestBody Map<String, Integer> data) {

        int puntos = data.getOrDefault("puntos", 10);
        boolean exito = puntosUsuarioService.sumarPuntos(idUsuario, puntos);

        if (exito) {
            return ResponseEntity.ok("✅ Se sumaron " + puntos + " puntos al usuario " + idUsuario +
                    " y se verificaron posibles premios.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ No se pudieron sumar los puntos");
        }
    }

    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<Map<String, Object>> consultarPuntosYPremios(@PathVariable int idUsuario) {
        Map<String, Object> respuesta = new HashMap<>();

        PuntosUsuario puntos = puntosUsuarioService.obtenerPuntosUsuario(idUsuario);
        respuesta.put("puntos", puntos != null ? puntos.getPuntosTotales() : 0);

        List<Map<String, String>> premios = premioUsuarioService.obtenerPremiosConInfo(idUsuario);
        respuesta.put("premios", premios);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
