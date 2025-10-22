package co.edu.unbosque.netflixbackend.model;

public class EstadoUsuSus {
	private int idEstado;
	private String nombreEstado;
	
	public EstadoUsuSus() {
		// TODO Auto-generated constructor stub
	}

	public EstadoUsuSus(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	

}
