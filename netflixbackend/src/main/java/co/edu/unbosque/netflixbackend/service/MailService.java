package co.edu.unbosque.netflixbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean enviarCodigoConfirmacion(String destinatario, String codigo) {
        if (destinatario == null || destinatario.isBlank()) {
            return false;
        }

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Código de Confirmación");
        mensaje.setText("Tu código de confirmación es: " + codigo);
        mensaje.setFrom("pokemonr649@gmail.com"); 

        javaMailSender.send(mensaje);
        return true;
    }

    public void enviarCorreoBienvenida(String destinatario) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("¡Bienvenido a NetflixBackend!");
        mensaje.setText("Gracias por registrarte. Esperamos que disfrutes de la plataforma.");
        mensaje.setFrom("pokemonr649@gmail.com"); 

        javaMailSender.send(mensaje);
    }


    
}
