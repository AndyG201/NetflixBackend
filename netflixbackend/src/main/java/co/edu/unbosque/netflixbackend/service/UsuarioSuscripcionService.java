package co.edu.unbosque.netflixbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.model.Pago;
import co.edu.unbosque.netflixbackend.repository.PagoRepository;
import co.edu.unbosque.netflixbackend.repository.UsuarioSuscripcionRepository;

@Service
public class UsuarioSuscripcionService {

	@Autowired
	private UsuarioSuscripcionRepository usuarioSuscripcionRepository;

	@Autowired
	private PagoRepository pagoRepository;

	public int adquirirSuscripcion(String referencia) {
		Pago pago = buscarPago(referencia);
		if (pago != null) {
			usuarioSuscripcionRepository.adquirirSuscripcion(pago.getIdSuscripcion(), pago.getIdUsuario());
			return 1;
		} else {
			return 0;
		}

	}

	public Pago buscarPago(String referencia) {
		Pago pago = pagoRepository.buscarPorReferencia(referencia);
		return pago;
	}

}
