package libreria.iei;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import libreria.bd.ServiciosPizzas;
import libreria.modelo.Articulo;
import libreria.modelo.Pedido;;

public class ValidarPedido implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ServiciosPizzas servicio = new ServiciosPizzas();
		Pedido miPedido = (Pedido) execution.getVariable("miPedido");
		List<Articulo> Linea = miPedido.getLinea();
		for(int i=0; i<Linea.size();i++) {
			double precio = servicio.obtenerPrecio(Linea.get(i).getNombrePizza());
			if(precio<0) {
				miPedido.eliminarPizza(Linea.get(i));
			}
		}
		
		if(miPedido.getLinea().isEmpty()) {
			execution.setVariable("PedidoValido", false);
		}else {
			execution.setVariable("PedidoValido", true);
		}
		execution.setVariable("miPedido", miPedido);
	}

}
