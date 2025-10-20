package co.edu.unbosque.netflixbackend.dto;

public class SuscripcionDTO {
	private int idSuscripcion;
	private String tipoSuscripcion;
	private int duracion;
	private int precio;
	private String descripcion;
	
	public SuscripcionDTO() {
		
	}
	
	public SuscripcionDTO(String tipoSuscripcion, int duracion, int precio, String descripcion) {
		super();
		this.tipoSuscripcion = tipoSuscripcion;
		this.duracion = duracion;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public String getTipoSuscripcion() {
		return tipoSuscripcion;
	}

	public void setTipoSuscripcion(String tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
 

}
