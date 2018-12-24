package libreria.iei;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import libreria.modelo.Pedido;
import libreria.modelo.Articulo;

public class AddPedido implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String NombreCliente = (String) execution.getVariable("IDNombre");
		String DireccionCliente = (String) execution.getVariable("IDDireccion");
		Date FechaPedido = (Date) execution.getVariable("IDFecha");
		//Accedemos a las variables del motor
		//que se corresponden con los campos de formularios
		
		Pedido pedido = new Pedido();
		pedido.setNombreCliente(NombreCliente);
		pedido.setDireccion(DireccionCliente);
		pedido.setFecha(FechaPedido);
		
		for(int i = 1; i<5;i++) {
			
			
			if(execution.getVariable("IDNombrePizza"+i)!= null && execution.getVariable("IDCantidad"+i) != null) {
				String NombrePizza = (String) execution.getVariable("IDNombrePizza"+i);
				Integer cantidad = ((Number) execution.getVariable("IDCantidad"+i)).intValue();
				Articulo Lista = new Articulo(NombrePizza,cantidad);
				pedido.AddLinea(Lista);
			}
		}
		//salvar el pedido en el motor para luego recuperarlo
		execution.setVariable("miPedido",pedido);
		
		
		
	}
	

}
