package co.edu.unbosque.netflixbackend.dto;

public class GeneroDTO {

	private int idGenero;
	private String nombre;
	
	public GeneroDTO() {
		// TODO Auto-generated constructor stub
	}

	public GeneroDTO( String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
