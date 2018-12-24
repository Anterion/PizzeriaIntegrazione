package libreria.modelo;

import java.io.Serializable;

public class Articulo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3453811740966019411L;
	private String NombrePizza;
	private int Cantidad;
	
	public Articulo(String NombrePizza, int Cantidad) {
		setNombrePizza(NombrePizza);
		setCantidad(Cantidad);
	}

	public String getNombrePizza() {
		return NombrePizza;
	}

	public void setNombrePizza(String nombrePizza) {
		NombrePizza = nombrePizza;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
}
