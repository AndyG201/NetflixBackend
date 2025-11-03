package co.edu.unbosque.netflixbackend.model;

public class PremioUsuario {

    private int idUsuario;
    private int idPremio;
    private String nombrePremio;
    private String descripcion;
    private String fechaOtorgado;
    private String fechaReclamado;
    private String estado;

    public PremioUsuario() {}

    public PremioUsuario(int idUsuario, int idPremio, String nombrePremio, String descripcion,
                         String fechaOtorgado, String fechaReclamado, String estado) {
        this.idUsuario = idUsuario;
        this.idPremio = idPremio;
        this.nombrePremio = nombrePremio;
        this.descripcion = descripcion;
        this.fechaOtorgado = fechaOtorgado;
        this.fechaReclamado = fechaReclamado;
        this.estado = estado;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdPremio() { return idPremio; }
    public void setIdPremio(int idPremio) { this.idPremio = idPremio; }

    public String getNombrePremio() { return nombrePremio; }
    public void setNombrePremio(String nombrePremio) { this.nombrePremio = nombrePremio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaOtorgado() { return fechaOtorgado; }
    public void setFechaOtorgado(String fechaOtorgado) { this.fechaOtorgado = fechaOtorgado; }

    public String getFechaReclamado() { return fechaReclamado; }
    public void setFechaReclamado(String fechaReclamado) { this.fechaReclamado = fechaReclamado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
