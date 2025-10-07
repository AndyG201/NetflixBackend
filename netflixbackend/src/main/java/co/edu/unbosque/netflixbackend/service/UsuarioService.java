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
    
    public int crearUsuario (String codigo) {
    	UsuarioDTO usuarioDTO = usuariosPendientes.get(codigo);
    	if(usuarioDTO != null) {
    		usuarioRepository.crearUsuario(modelMapper.map(usuarioDTO, Usuario.class));
    		usuariosPendientes.remove(codigo);
    		return 1;
    	}
    	return 0;
    }

	public boolean buscarPorCorreo (String correo) {
		Usuario usuario = usuarioRepository.findByEmail(correo);
		if(usuario == null) {
			return false;
		}else {
			return true;
		}
	}
	
	

	
}
