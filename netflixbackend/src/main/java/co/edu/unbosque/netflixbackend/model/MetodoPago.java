<<<<<<< HEAD
package co.edu.unbosque.netflixbackend.model;

public class MetodoPago {

	private int idMetodoPago;
	private String metodo;
	

	public MetodoPago() {
		// TODO Auto-generated constructor stub
	}
	

	public MetodoPago(int idMetodoPago, String metodo) {
		super();
		this.idMetodoPago = idMetodoPago;
		this.metodo = metodo;
	}


	public MetodoPago(String metodo) {
		this.metodo = metodo;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	
}
=======
package co.edu.unbosque.netflixbackend.model;

public class MetodoPago {

	private int idMetodoPago;
	private String metodo;
	
	public MetodoPago(int idMetodoPago, String metodo) {
		this.idMetodoPago = idMetodoPago;
		this.metodo = metodo;
	}

	public MetodoPago() {
		// TODO Auto-generated constructor stub
	}
	




	public MetodoPago(String metodo) {
		this.metodo = metodo;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	
}
>>>>>>> branch 'main' of https://github.com/AndyG201/NetflixBackend.git
