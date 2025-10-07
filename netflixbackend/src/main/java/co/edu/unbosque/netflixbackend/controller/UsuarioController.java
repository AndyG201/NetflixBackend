package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.netflixbackend.dto.UsuarioDTO;
import co.edu.unbosque.netflixbackend.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/enviarcodigo")
	public ResponseEntity<String> enviarCodigo (@RequestBody UsuarioDTO usuarioDTO){
		int rta = usuarioService.enviarCodigo(usuarioDTO);
		if(rta==1) {
			return new ResponseEntity<>("Codigo enviado", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("No se logro enviar el codigo", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("crearusuario")
	public ResponseEntity<String> crearUsuario (@RequestBody String codigo){
		int rta = usuarioService.crearUsuario(codigo);
		if(rta == 1) {
			return new ResponseEntity<>("UsuarioCreado", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("No se logro crear", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
