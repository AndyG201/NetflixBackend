package co.edu.unbosque.netflixbackend.model;

public class Serie {
    private int idSerie;
    private String nombre;
    private String genero;
    private String descripcion;
    private int idClasificacion;

    public Serie() {
    	
    }

    public Serie(int idSerie, String nombre, String genero, String descripcion, int idClasificacion) {
        this.idSerie = idSerie;
        this.nombre = nombre;
        this.genero = genero;
        this.descripcion = descripcion;
        this.idClasificacion = idClasificacion;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "idSerie=" + idSerie +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idClasificacion=" + idClasificacion +
                '}';
    }
}
