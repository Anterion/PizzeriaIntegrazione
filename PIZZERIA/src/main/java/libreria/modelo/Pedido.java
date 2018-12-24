package libreria.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2275529295272605254L;
	private String NombreCliente;
	private String Direccion;
	private Date Fecha;
	private double Total;
	private List<Articulo> Linea;
	
	public Pedido() {
		Linea = new ArrayList<Articulo>();
		setTotal(0.0);
	}

	public String getNombreCliente() {
		return NombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		NombreCliente = nombreCliente;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	
	public void AddLinea(Articulo itemLinea) {
		Linea.add(itemLinea);
	}
	
	public List<Articulo> getLinea(){
		return Linea;
	}
	
	public void eliminarPizza(Articulo itemEliminar) {
		Linea.remove(itemEliminar);
	}

	public double getTotal() {
		return Total;
	}

	private void setTotal(double total) {
		Total = total;
	}
	
	public void addPrecio(double cantidad) {
		setTotal(getTotal()+cantidad);
	}
	
	
	
	

}
