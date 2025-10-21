package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDate;

public class Registro {
	
	private int idRegistro;
	private LocalDate fechaRegistro;
	
	public Registro() {
		// TODO Auto-generated constructor stub
	}

	public Registro(LocalDate fechaRegistro) {
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
