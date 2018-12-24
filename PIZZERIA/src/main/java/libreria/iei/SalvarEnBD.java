package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import libreria.bd.ServiciosPizzas;
import libreria.modelo.Articulo;
import libreria.modelo.Pedido;

public class SalvarEnBD implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Este metodo consulta todos los datos y realiza un guardado en las dos tablas
		//de la base de datos
		
		ServiciosPizzas servicio = new ServiciosPizzas();
		Pedido miPedido = (Pedido) execution.getVariable("miPedido");
		
		int idPedido = servicio.insertarCabeceraPedido(miPedido.getNombreCliente(), miPedido.getDireccion(),
				miPedido.getTotal(), miPedido.getFecha());
		
		for(Articulo pizza:miPedido.getLinea()) {
			double precio = servicio.obtenerPrecio(pizza.getNombrePizza());
			servicio.insertarLineaPedido(idPedido, pizza.getNombrePizza(), 
					pizza.getCantidad(), precio*pizza.getCantidad());
		}
		
	}

}
