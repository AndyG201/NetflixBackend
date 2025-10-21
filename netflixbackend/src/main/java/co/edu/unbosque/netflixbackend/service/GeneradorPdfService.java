package co.edu.unbosque.netflixbackend.service;

import java.awt.Color;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.netflixbackend.model.Pago;
import co.edu.unbosque.netflixbackend.model.Suscripcion;
import co.edu.unbosque.netflixbackend.model.Usuario;
import co.edu.unbosque.netflixbackend.repository.PagoRepository;
import co.edu.unbosque.netflixbackend.repository.SuscripcionRepository;
import co.edu.unbosque.netflixbackend.repository.UsuarioRepository;

@Service
public class GeneradorPdfService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private MailService mailService;

	   public String crearPdf(String referencia) {
	        Pago pago = pagoRepository.buscarPorReferencia(referencia);
	        if (pago == null) {
	            return "❌ No se encontró el pago con referencia: " + referencia;
	        }

	        Usuario usuario = buscarUsuario(pago.getIdUsuario());
	        Suscripcion suscripcion = buscarSuscripcion(pago.getIdSuscripcion());

	        String rutaArchivo = "recibo_pago_" + usuario.getPrimerNombre() + "_" + referencia + ".pdf";

	        try {
	        	
	        	Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
	        	PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
	        	documento.open();

	        	
	        	Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.RED);
	        	Font seccionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new BaseColor(40, 40, 40));
	        	Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(60, 60, 60));
	        	Font smallFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, new BaseColor(100, 100, 100));

	        	
	        	Image logo = Image.getInstance("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPhj-w0fx_8l8vEgDbIJN1iPEwRoEBU8nPEQ&s");
	        	logo.scaleToFit(120, 60);
	        	logo.setAlignment(Element.ALIGN_CENTER);
	        	documento.add(logo);

	        	Paragraph titulo = new Paragraph("RECIBO DE PAGO - NETFLIX", tituloFont);
	        	titulo.setAlignment(Element.ALIGN_CENTER);
	        	documento.add(titulo);

	        	documento.add(new Paragraph(" ", textoFont));
	        	documento.add(new Paragraph("──────────────────────────────────────────────", smallFont));
	        	documento.add(new Paragraph(" ", textoFont));

	        	
	        	PdfPTable tablaPago = new PdfPTable(2);
	        	tablaPago.setWidthPercentage(100);
	        	tablaPago.setSpacingBefore(10f);
	        	tablaPago.setSpacingAfter(10f);
	        	tablaPago.setWidths(new float[]{1.5f, 2f});

	        	tablaPago.addCell(new PdfPCell(new Phrase("📅 Fecha de pago:", seccionFont)));
	        	tablaPago.addCell(new PdfPCell(new Phrase(pago.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), textoFont)));

	        	tablaPago.addCell(new PdfPCell(new Phrase("💳 Referencia:", seccionFont)));
	        	tablaPago.addCell(new PdfPCell(new Phrase(pago.getReferencia(), textoFont)));

	        	tablaPago.addCell(new PdfPCell(new Phrase("💰 Monto:", seccionFont)));
	        	tablaPago.addCell(new PdfPCell(new Phrase("$" + pago.getMonto(), textoFont)));


	        	documento.add(tablaPago);

	        	
	        	documento.add(new Paragraph("👤 DATOS DEL USUARIO", seccionFont));
	        	documento.add(new Paragraph("Nombre: " + usuario.getPrimerNombre() + " " + usuario.getPrimerApellido(), textoFont));
	        	documento.add(new Paragraph("Correo: " + usuario.getCorreo(), textoFont));
	        	documento.add(new Paragraph("Teléfono: " + usuario.getTelefono(), textoFont));
	        	documento.add(new Paragraph(" ", textoFont));

	        	
	        	documento.add(new Paragraph("🎬 DATOS DE LA SUSCRIPCIÓN", seccionFont));
	        	documento.add(new Paragraph("Tipo: " + suscripcion.getTipoSuscripcion(), textoFont));
	        	documento.add(new Paragraph("Duración: " + suscripcion.getDuracion() + " dias", textoFont));
	        	documento.add(new Paragraph("Precio: $" + suscripcion.getPrecio(), textoFont));
	        	documento.add(new Paragraph("Descripción: " + suscripcion.getDescripcion(), textoFont));
	        	documento.add(new Paragraph(" ", textoFont));

	        	
	        	documento.add(new Paragraph("──────────────────────────────────────────────", smallFont));
	        	Paragraph pie = new Paragraph("Gracias por tu suscripción a Netflix 🎥\n", textoFont);
	        	pie.setAlignment(Element.ALIGN_CENTER);
	        	documento.add(pie);

	        	Paragraph contacto = new Paragraph("Si tienes dudas, contáctanos en soporte@netflix.com", smallFont);
	        	contacto.setAlignment(Element.ALIGN_CENTER);
	        	documento.add(contacto);

	        	documento.close();


	            boolean enviado = mailService.enviarCorreoConRecibo(usuario.getCorreo(), rutaArchivo);
	            if (enviado) {
	                return "✅ Recibo generado y enviado correctamente a " + usuario.getCorreo();
	            } else {
	                return "⚠️ Recibo generado, pero no se pudo enviar el correo.";
	            }

	        } catch (DocumentException | java.io.IOException e) {
	            e.printStackTrace();
	            return "❌ Error al generar o enviar el PDF: " + e.getMessage();
	        }
	    }

	public Usuario buscarUsuario(int idUsuario) {
		Usuario found = usuarioRepository.findById(idUsuario);
		return found;
	}

	public Suscripcion buscarSuscripcion(int idSuscripcion) {
		Suscripcion found = suscripcionRepository.findById(idSuscripcion);
		return found;
	}

}
