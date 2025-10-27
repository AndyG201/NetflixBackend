package co.edu.unbosque.netflixbackend.model;

public class Genero {

	private int idGenero;
	private String nombre;
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public Genero( String nombre) {
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
