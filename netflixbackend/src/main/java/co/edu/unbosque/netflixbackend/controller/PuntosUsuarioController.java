package co.edu.unbosque.netflixbackend.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.netflixbackend.model.PuntosUsuario;
import co.edu.unbosque.netflixbackend.model.PremioUsuario;
import co.edu.unbosque.netflixbackend.service.PuntosUsuarioService;
import co.edu.unbosque.netflixbackend.service.PremioUsuarioService;

import java.util.*;

@RestController
@RequestMapping("/puntos")
@CrossOrigin(origins = "*")
public class PuntosUsuarioController {

    @Autowired
    private PuntosUsuarioService puntosUsuarioService;

    @Autowired
    private PremioUsuarioService premioUsuarioService;

    // ✅ Endpoint para sumar puntos al usuario de manera dinámica
    @PostMapping("/sumar/{idUsuario}")
    public ResponseEntity<String> sumarPuntos(
            @PathVariable int idUsuario,
            @RequestBody Map<String, Integer> data) {

        int puntos = data.getOrDefault("puntos", 10); // Si no envía, suma 10 por defecto
        boolean exito = puntosUsuarioService.sumarPuntos(idUsuario, puntos);

        if (exito) {
            return ResponseEntity.ok("✅ Se sumaron " + puntos + " puntos al usuario " + idUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ No se pudieron sumar los puntos");
        }
    }

    // ✅ Endpoint para consultar puntos y premios
    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<Map<String, Object>> consultarPuntosYPremios(@PathVariable int idUsuario) {
        Map<String, Object> respuesta = new HashMap<>();

        PuntosUsuario puntos = puntosUsuarioService.obtenerPuntosUsuario(idUsuario);
        int puntosTotales = (puntos != null) ? puntos.getPuntosTotales() : 0;
        respuesta.put("puntos", puntosTotales);

        List<PremioUsuario> premios = premioUsuarioService.obtenerPremiosPorUsuario(idUsuario);
        respuesta.put("premios", premios != null ? premios : new ArrayList<>());

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
