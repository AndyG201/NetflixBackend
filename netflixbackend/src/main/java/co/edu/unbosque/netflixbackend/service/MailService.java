package co.edu.unbosque.netflixbackend.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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
        mensaje.setFrom("baseflix91@gmail.com"); 

        javaMailSender.send(mensaje);
        return true;
    }
    
    public boolean enviarCodigoReferencia(String destinatario, String referencia) {
        if (destinatario == null || destinatario.isBlank()) {
            return false;
        }

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Refrencia de pago");
        mensaje.setText("Tu referencia para hacer el pago de tu suscripción es: " + referencia);
        mensaje.setFrom("baseflix91@gmail.com"); 

        javaMailSender.send(mensaje);
        return true;
    }

    public void enviarCorreoBienvenida(String destinatario) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("¡Bienvenido a baseflix91");
        mensaje.setText("Gracias por registrarte. Esperamos que disfrutes de la plataforma.");
        mensaje.setFrom("baseflix91@gmail.com"); 

        javaMailSender.send(mensaje);
    }

    public boolean enviarCorreoConRecibo(String destinatario, String rutaPdf) {
        try {
            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(destinatario);
            helper.setSubject("Recibo de Pago - baseflix91");
            helper.setText("""
                    Hola 👋,
                    
                    Adjuntamos tu recibo de pago correspondiente a tu suscripción en baseflix91.
                    
                    Gracias por confiar en nosotros y disfrutar de nuestro contenido 🎬🍿
                    
                    — El equipo de baseflix91
                    """, false);
            helper.setFrom("baseflix91@gmail.com");

            File archivo = new File(rutaPdf);
            if (!archivo.exists()) {
                System.err.println("⚠️ No se encontró el archivo PDF: " + rutaPdf);
                return false;
            }

            helper.addAttachment("Recibo_Pago.pdf", archivo);

            javaMailSender.send(mensaje);
            System.out.println("✅ Recibo de pago enviado correctamente a: " + destinatario);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    


    
}
