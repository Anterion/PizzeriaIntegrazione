package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import libreria.modelo.Articulo;
import libreria.modelo.Pedido;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class EnviarTweet implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Modificar la clase para adaptarla a el nuevo tipo Pedido
		Pedido miPedido = (Pedido) execution.getVariable("miPedido");
		String nombreCliente = miPedido.getNombreCliente();
		//int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();
		//String pizza = (String) execution.getVariable("IDNombrePizza");
		double precio = miPedido.getTotal();
		String entrega = (String) execution.getVariable("IDEntrega");
		String consumerKey = "";
		String consumerSecret = "";
		String accessToken = "";
		String accessTokenSecret = "";
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
		.setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken)
		.setOAuthAccessTokenSecret(accessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try {
			String pizzas="";
			for(Articulo pizza: miPedido.getLinea()) {
				pizzas=pizzas+" "+pizza.getNombrePizza();
			}
			
			twitter.updateStatus("Se ha comenzado a hornear  el pedido de "
			+miPedido.getNombreCliente()+" pizzas: "+pizzas
			+ " y el importe es: "+precio +" y serán entregadas en "+entrega+" minutos");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
