package co.edu.unbosque.netflixbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.netflixbackend.model.Pago;
import co.edu.unbosque.netflixbackend.model.Suscripcion;
import co.edu.unbosque.netflixbackend.model.Usuario;
import co.edu.unbosque.netflixbackend.repository.PagoRepository;
import co.edu.unbosque.netflixbackend.repository.SuscripcionRepository;
import co.edu.unbosque.netflixbackend.repository.UsuarioRepository;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public boolean crearPago (Pago pago) {
		String referencia =  generarReferencia();
		pago.setReferencia(referencia);
		pago.setIdEstadoPago(1);
		pago.setIdMetodoPago(1);
		pago.setMonto(buscarMontoPago(pago.getIdSuscripcion()));
		boolean rta = pagoRepository.crearPago(modelMapper.map(pago, Pago.class));
		mailService.enviarCodigoReferencia(buscarCorreoUsuario(pago.getIdUsuario()), referencia);
		return rta;
	}
	
	public String buscarCorreoUsuario (int idUsuario) {
		Usuario found = usuarioRepository.findById(idUsuario);
		return found.getCorreo();
	}

	public String generarReferencia() {
	    int numero = (int) (100_000_000 + Math.random() * 900_000_000);
	    return "REF-" + numero;
	}
	
	public int buscarMontoPago (int idSuscripcion) {
		Suscripcion found = suscripcionRepository.findById(idSuscripcion);
		return found.getPrecio();
	}

}
