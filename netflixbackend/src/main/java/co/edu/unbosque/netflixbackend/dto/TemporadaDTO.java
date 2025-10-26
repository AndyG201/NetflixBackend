package co.edu.unbosque.netflixbackend.dto;

public class TemporadaDTO {
	
	private int idTemporada;
    private int numeroTemporada;
    private int anioEstreno;
    private int idSerie;

    public TemporadaDTO() {
    	
    }

	public TemporadaDTO(int numeroTemporada, int anioEstreno, int idSerie) {
		super();
		this.numeroTemporada = numeroTemporada;
		this.anioEstreno = anioEstreno;
		this.idSerie = idSerie;
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

	public int getNumeroTemporada() {
		return numeroTemporada;
	}

	public void setNumeroTemporada(int numeroTemporada) {
		this.numeroTemporada = numeroTemporada;
	}

	public int getAnioEstreno() {
		return anioEstreno;
	}

	public void setAnioEstreno(int anioEstreno) {
		this.anioEstreno = anioEstreno;
	}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

}
