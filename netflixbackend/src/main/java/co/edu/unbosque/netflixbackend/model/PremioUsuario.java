package co.edu.unbosque.netflixbackend.model;
public class PremioUsuario {

 
    private int idUsuario;
    private int idPremio;
    private String fechaOtorgado;
    private String fechaReclamado;
    private String estado;
    
    public PremioUsuario() {
		// TODO Auto-generated constructor stub
	}
    
	public PremioUsuario(int idPremio, String fechaOtorgado, String fechaReclamado, String estado) {
		super();
		this.idPremio = idPremio;
		this.fechaOtorgado = fechaOtorgado;
		this.fechaReclamado = fechaReclamado;
		this.estado = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(int idPremio) {
		this.idPremio = idPremio;
	}

	public String getFechaOtorgado() {
		return fechaOtorgado;
	}

	public void setFechaOtorgado(String fechaOtorgado) {
		this.fechaOtorgado = fechaOtorgado;
	}

	public String getFechaReclamado() {
		return fechaReclamado;
	}

	public void setFechaReclamado(String fechaReclamado) {
		this.fechaReclamado = fechaReclamado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

   
}
