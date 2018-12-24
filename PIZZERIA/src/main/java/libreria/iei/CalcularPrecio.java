package libreria.iei;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import libreria.bd.ServiciosPizzas;
import libreria.modelo.Articulo;
import libreria.modelo.Pedido;
public class CalcularPrecio implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//String NombrePizza = (String) execution.getVariable("IDNombrePizza"); 
		// accede a la variable IDNombrePizza, el método devuelve un Object, se realiza un casting
		// Si la variable no está en el motor devolverá null
		/*int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();
		System.out.println("Calculando el precio de " + cantidad + " " + NombrePizza + "pizzas");
		double precio = 0.0;
		if("margarita".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 6.0; }
		else { precio = cantidad * 8.0; }
			System.out.println("El precio será " + precio);
			execution.setVariable("IDPrecio", precio); // Pone un valor double en el motor*/
		
			
		//Consultar en la base de datos los precios de las pizzas que hay en los pedidos,
		//y dicha cantidad añadirla al objeto de tipo Precio, para poder guardarlo.
		ServiciosPizzas servicio = new ServiciosPizzas();
		Pedido miPedido = (Pedido) execution.getVariable("miPedido");
		for(Articulo pizza : miPedido.getLinea()) {
			double precio = servicio.obtenerPrecio(pizza.getNombrePizza());
			miPedido.addPrecio(precio*pizza.getCantidad());
		}
		
		System.out.println("El total asciende a "+miPedido.getTotal());
		execution.setVariable("miPedido", miPedido);
	}
}
