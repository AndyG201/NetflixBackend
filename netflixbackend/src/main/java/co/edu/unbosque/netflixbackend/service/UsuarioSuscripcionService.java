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

    public int adquirirSuscripcion(int idSuscripcion, int idUsuario) {
        return usuarioSuscripcionRepository.adquirirSuscripcion(idSuscripcion, idUsuario);
    }


    public Pago buscarPago(String referencia) {
		Pago pago = pagoRepository.buscarPorReferencia(referencia);
		return pago;
	}

}
