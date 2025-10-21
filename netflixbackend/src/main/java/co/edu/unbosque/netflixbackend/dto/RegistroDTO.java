package co.edu.unbosque.netflixbackend.dto;

import java.time.LocalDate;

public class RegistroDTO {
	
	private int idRegistro;
	private LocalDate fechaRegistro;
	
	public RegistroDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegistroDTO(LocalDate fechaRegistro) {
		super();
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	

}
