package co.edu.unbosque.netflixbackend.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Usuario;
import co.edu.unbosque.netflixbackend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailService mailService;

    private static final SecureRandom RANDOM = new SecureRandom();
    private Map<String, Usuario> usuariosPendientes = new HashMap<>();

    public String generarCodigoConfirmacion() {
        int codigo = RANDOM.nextInt(100000) + 900000;
        return String.valueOf(codigo);
    }

    public int enviarCodigo(Usuario usuario) {
        boolean encontrado = buscarPorCorreo(usuario.getCorreo());
        if (!encontrado) {
            String codigo = generarCodigoConfirmacion();
            usuariosPendientes.put(codigo, usuario);
            boolean enviado = mailService.enviarCodigoConfirmacion(usuario.getCorreo(), codigo);
            if (!enviado) {
                return 0;
            }
            return 1;
        }
        return 0;
    }

    public int crearUsuario(String codigo) {
        codigo = codigo.replace("\"", "").trim();
        Usuario usuario = usuariosPendientes.get(codigo);
        if (usuario != null) {
            usuario.setFechaRegistro(LocalDateTime.now());
            usuario.setIdEstado(1);
            usuario.setPrimeraVez(false);
            boolean creado = usuarioRepository.crearUsuario(usuario);
            if (creado) {
                usuariosPendientes.remove(codigo);
                return 1;
            }
        }
        return 0;
    }

    public Usuario login(String correo, String contrasenia) {
        Usuario usuario = usuarioRepository.login(correo, contrasenia);
        if (usuario == null) {
            return null;
        }

        if (usuario.isPrimeraVez()) {
            usuarioRepository.actualizarPrimeraVez(usuario.getIdUsuario(), false);
            usuario.setPrimeraVez(true);
        } else {
            usuario.setPrimeraVez(false);
        }

        return usuario;
    }


    public boolean buscarPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByEmail(correo);
        return usuario != null;
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByEmail(correo);
    }
}
