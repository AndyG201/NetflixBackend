package co.edu.unbosque.netflixbackend.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.PagoDTO;
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

    @Autowired
    private GeneradorPdfService generadorPdfService; // <- inyectar el generador para crear+enviar pdf

    /**
     * Crea el pago, genera la referencia, guarda en BD, envía la referencia por correo
     * y genera + adjunta el PDF (llama a GeneradorPdfService).
     *
     * Devuelve la referencia si todo sale bien, o null si falla.
     */
    public String crearPago(PagoDTO pagoDTO) {
        // 1) Validaciones mínimas
        if (pagoDTO == null) {
            throw new IllegalArgumentException("PagoDTO es null");
        }

        int idSuscripcion = pagoDTO.getIdSuscripcion();
        int idUsuario = pagoDTO.getIdUsuario();

        // Validar suscripción
        Suscripcion sus = suscripcionRepository.findById(idSuscripcion);
        if (sus == null) {
            throw new RuntimeException("Suscripción con id " + idSuscripcion + " no encontrada");
        }

        // Validar usuario
        Usuario user = usuarioRepository.findById(idUsuario);
        if (user == null) {
            throw new RuntimeException("Usuario con id " + idUsuario + " no encontrado");
        }

        // 2) Generar referencia y completar campos obligatorios
        String referencia = generarReferencia();
        pagoDTO.setReferencia(referencia);
        pagoDTO.setIdEstadoPago(1);
        pagoDTO.setIdMetodoPago(1);
        // Aseguramos que el monto venga desde la suscripción en BD
        pagoDTO.setMonto(sus.getPrecio());

        // 3) Establecer fecha (hoy) si no viene
        if (pagoDTO.getFecha() == null) {
            pagoDTO.setFecha(LocalDate.now());
        }

        // 4) Mapear y guardar en BD
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        boolean creado = pagoRepository.crearPago(pago);

        if (!creado) {
            throw new RuntimeException("No se pudo crear el pago en la base de datos");
        }

        // 5) Enviar referencia por correo (notificación de referencia)
        mailService.enviarCodigoReferencia(user.getCorreo(), referencia);

        // 6) Generar PDF y enviarlo al correo (GeneradorPdfService retorna mensaje)
        String resultadoPdf = generadorPdfService.crearPdf(referencia);
        // puedes loguear resultadoPdf para debug
        System.out.println("Resultado generación PDF: " + resultadoPdf);

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

    // opcional: mantener método antiguo pero con validación
    public int buscarMontoPago(int idSuscripcion) {
        Suscripcion found = suscripcionRepository.findById(idSuscripcion);
        if (found == null) {
            throw new RuntimeException("Suscripción con id " + idSuscripcion + " no encontrada");
        }
        return found.getPrecio();
    }

}
