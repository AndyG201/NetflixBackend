package co.edu.unbosque.netflixbackend.service;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.dto.UsuarioDTO;
import co.edu.unbosque.netflixbackend.model.Usuario;
import co.edu.unbosque.netflixbackend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MailService mailService;
	
	private static final SecureRandom RANDOM = new SecureRandom();
    private Map<String, UsuarioDTO> usuariosPendientes = new HashMap<>();
    
    public String generarCodigoConfirmacion() {
        int codigo = RANDOM.nextInt(100000) + 900000;
        return String.valueOf(codigo);
    }
    
    public int enviarCodigo(UsuarioDTO usuarioDTO) {
       boolean encontrado = buscarPorCorreo(usuarioDTO.getCorreo());
       if(!encontrado) {
    	   String codigo = generarCodigoConfirmacion();
    	   usuariosPendientes.put(codigo, usuarioDTO);
    	   boolean enviado = mailService.enviarCodigoConfirmacion(usuarioDTO.getCorreo(), codigo);
    	   if(!enviado) {
    		   return 0;
    	   }
    	   return 1;
       }
       return 0;
    }

    public int crearUsuario(String codigo) {
        codigo = codigo.replace("\"", "").trim();

        UsuarioDTO usuarioDTO = usuariosPendientes.get(codigo);

        if (usuarioDTO != null) {
            // ✅ Agregar datos faltantes antes de guardar
            usuarioDTO.setFechaRegistro(java.time.LocalDateTime.now());
            usuarioDTO.setIdEstado(1); // Por ejemplo, 1 = activo o verificado

            Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

            boolean creado = usuarioRepository.crearUsuario(usuario);

            if (creado) {
                usuariosPendientes.remove(codigo);
                return 1;
            }
        }

        return 0;
    }


    public UsuarioDTO login(String correo, String contrasenia) {
        try {
            Usuario usuario = usuarioRepository.login(correo, contrasenia);

            if (usuario == null) {
                System.out.println("⚠️ Usuario no encontrado con el correo: " + correo);
                return null;
            }

            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

            // Si es la primera vez, actualizamos la DB para marcar que ya no es primera vez
            if (usuario.isPrimeraVez()) {
                boolean actualizado = usuarioRepository.actualizarPrimeraVez(usuario.getIdUsuario(), false);
                if (actualizado) {
                    usuarioDTO.setPrimeraVez(true); // indicar al frontend que SI era primera vez
                } else {
                    // Si falla la actualización, aún podemos devolver true para que vaya a suscripciones,
                    // pero idealmente loggear el error.
                    usuarioDTO.setPrimeraVez(true);
                    System.err.println("No se pudo actualizar primera_vez para id " + usuario.getIdUsuario());
                }
            } else {
                usuarioDTO.setPrimeraVez(false);
            }

            System.out.println("✅ Usuario autenticado correctamente: " + usuario.getCorreo());
            return usuarioDTO;

        } catch (Exception e) {
            System.err.println("❌ Error durante el inicio de sesión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    public boolean buscarPorCorreo (String correo) {
		Usuario usuario = usuarioRepository.findByEmail(correo);
		if(usuario == null) {
			return false;
		}else {
			return true;
		}
	}

    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByEmail(correo);
        if (usuario == null) {
            return null;
        }
        return modelMapper.map(usuario, UsuarioDTO.class);
    }



}
