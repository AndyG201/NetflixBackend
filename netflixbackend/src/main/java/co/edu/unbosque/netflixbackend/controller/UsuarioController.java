package co.edu.unbosque.netflixbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.netflixbackend.model.Usuario;
import co.edu.unbosque.netflixbackend.service.UsuarioService;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	//si funciona 
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/enviarcodigo")
	public ResponseEntity<String> enviarCodigo (@RequestBody Usuario usuarioDTO){
		int rta = usuarioService.enviarCodigo(usuarioDTO);
		if(rta==1) {
			return new ResponseEntity<>("Codigo enviado", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("No se logro enviar el codigo", HttpStatus.NOT_ACCEPTABLE);
		}
	}

    @PostMapping("/crearusuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Map<String, String> data) {
        String codigo = data.get("codigo");
        System.out.println("📩 Verificando código recibido: " + codigo);

        int rta = usuarioService.crearUsuario(codigo);
        if (rta == 1) {
            return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No se logró crear", HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasenia) {
        Usuario usuario = usuarioService.login(correo, contrasenia);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/actual")
    public ResponseEntity<?> obtenerUsuarioActual(@RequestParam String correo) {
        Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        return ResponseEntity.ok(usuario);
    }


}
