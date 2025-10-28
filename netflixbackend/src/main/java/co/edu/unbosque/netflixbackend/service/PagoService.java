package co.edu.unbosque.netflixbackend.service;

import java.time.LocalDate;
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
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeneradorPdfService generadorPdfService;

    public String crearPago(Pago pago) {
        if (pago == null) {
            throw new IllegalArgumentException("El objeto Pago no puede ser null");
        }

        int idSuscripcion = pago.getIdSuscripcion();
        int idUsuario = pago.getIdUsuario();

        Suscripcion sus = suscripcionRepository.findById(idSuscripcion);
        if (sus == null) {
            throw new RuntimeException("Suscripción con id " + idSuscripcion + " no encontrada");
        }

        Usuario user = usuarioRepository.findById(idUsuario);
        if (user == null) {
            throw new RuntimeException("Usuario con id " + idUsuario + " no encontrado");
        }

        String referencia = generarReferencia();
        pago.setReferencia(referencia);
        pago.setIdEstadoPago(1);
        pago.setIdMetodoPago(1);
        pago.setMonto(sus.getPrecio());

        if (pago.getFecha() == null) {
            pago.setFecha(LocalDate.now());
        }

        boolean creado = pagoRepository.crearPago(pago);
        if (!creado) {
            throw new RuntimeException("No se pudo crear el pago en la base de datos");
        }

        mailService.enviarCodigoReferencia(user.getCorreo(), referencia);
        generadorPdfService.crearPdf(referencia);
        return referencia;
    }

    public String buscarCorreoUsuario(int idUsuario) {
        Usuario found = usuarioRepository.findById(idUsuario);
        return (found != null) ? found.getCorreo() : null;
    }

    public String generarReferencia() {
        int numero = (int) (100_000_000 + Math.random() * 900_000_000);
        return "REF-" + numero;
    }

    public int buscarMontoPago(int idSuscripcion) {
        Suscripcion found = suscripcionRepository.findById(idSuscripcion);
        if (found == null) {
            throw new RuntimeException("Suscripción con id " + idSuscripcion + " no encontrada");
        }
        return found.getPrecio();
    }
}
