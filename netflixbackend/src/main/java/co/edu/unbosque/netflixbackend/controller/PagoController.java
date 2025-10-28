<<<<<<< HEAD
package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.netflixbackend.model.Pago;
import co.edu.unbosque.netflixbackend.service.GeneradorPdfService;
import co.edu.unbosque.netflixbackend.service.PagoService;
import co.edu.unbosque.netflixbackend.service.UsuarioSuscripcionService;

@RestController
@RequestMapping("/pago")
@CrossOrigin(origins = { "*" })
public class PagoController {
	
	@Autowired
	private PagoService pagoService;
	
	@Autowired
	private UsuarioSuscripcionService usuarioSuscripcionService;
	
	@Autowired
	private GeneradorPdfService generadorPdfService;
	
	@PostMapping
	public ResponseEntity<String> crearPago (@RequestBody Pago pago){
		boolean rta = pagoService.crearPago(pago);
		if(rta) {
			return new ResponseEntity<String>("Pago creado", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Pago no creado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<String> enviarPdf (@RequestParam String referencia){
		usuarioSuscripcionService.adquirirSuscripcion(referencia);
		return new ResponseEntity<String>(generadorPdfService.crearPdf(referencia),HttpStatus.ACCEPTED);
	}

}
=======
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
>>>>>>> branch 'main' of https://github.com/AndyG201/NetflixBackend.git
