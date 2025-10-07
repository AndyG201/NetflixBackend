package co.edu.unbosque.netflixbackend.model;

public class Temporada {
    private int idTemporada;
    private int numeroTemporada;
    private java.sql.Date anioEstreno;
    private int serieIdSerie;

    public Temporada() {
    	
    }

    public Temporada(int idTemporada, int numeroTemporada, java.sql.Date anioEstreno, int serieIdSerie) {
        this.idTemporada = idTemporada;
        this.numeroTemporada = numeroTemporada;
        this.anioEstreno = anioEstreno;
        this.serieIdSerie = serieIdSerie;
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

    public java.sql.Date getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(java.sql.Date anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public int getSerieIdSerie() {
        return serieIdSerie;
    }

    public void setSerieIdSerie(int serieIdSerie) {
        this.serieIdSerie = serieIdSerie;
    }


    @Override
    public String toString() {
        return "Temporada{" +
                "idTemporada=" + idTemporada +
                ", numeroTemporada=" + numeroTemporada +
                ", anioEstreno=" + anioEstreno +
                ", serieIdSerie=" + serieIdSerie +
                '}';
    }
}
