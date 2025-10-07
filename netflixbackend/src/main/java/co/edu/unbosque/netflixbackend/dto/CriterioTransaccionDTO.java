package co.edu.unbosque.netflixbackend.dto;

public class CriterioTransaccionDTO {

	public int idCriterioTransaccion;
	public String criterio;
	
	public CriterioTransaccionDTO() {
		// TODO Auto-generated constructor stub
	}

	public CriterioTransaccionDTO(String criterio) {
		super();
		this.criterio = criterio;
	}

	public int getIdCriterioTransaccion() {
		return idCriterioTransaccion;
	}

	public void setIdCriterioTransaccion(int idCriterioTransaccion) {
		this.idCriterioTransaccion = idCriterioTransaccion;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	@Override
	public String toString() {
		return "CriterioTransaccion [idCriterioTransaccion=" + idCriterioTransaccion + ", criterio=" + criterio + "]";
	}
	
}
