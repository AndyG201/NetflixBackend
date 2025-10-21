package co.edu.unbosque.netflixbackend.model;

public class Documental {

    private int idDocumental; 
    private String nombre; 
    private String descripcion; 
    private String poster; 
    private String fechaEstreno; 
    private double calificacion; 
    private double popularidad;

    public Documental() {}

    public Documental(int idDocumental, String nombre, String descripcion, String poster, 
                      String fechaEstreno, double calificacion, double popularidad) {
        this.idDocumental = idDocumental;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.poster = poster;
        this.fechaEstreno = fechaEstreno;
        this.calificacion = calificacion;
        this.popularidad = popularidad;
    }

    public int getIdDocumental() {
        return idDocumental;
    }

    public void setIdDocumental(int idDocumental) {
        this.idDocumental = idDocumental;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(double popularidad) {
        this.popularidad = popularidad;
    }

}
