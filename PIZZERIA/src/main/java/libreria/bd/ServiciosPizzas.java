package libreria.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 * Clase de utilidades de consulta a base de datos de entrega3procesos
 * @author Administrador
 *
 */
public class ServiciosPizzas {
	/**
	 * Metodo que se utiliza para obtener el Precio de una pizza. Si la pizza no existe, este
	 * devolverá el valor -1.0.
	 * @param nombrePizza Nombre de la pizza de la que se desea buscar el precio.
	 * @return Importe de la pizza. En caso de no existir la pizza, devolvera -1.0
	 */
	public double obtenerPrecio(String nombrePizza) {
		double importe = -1.0;
		Connection conn = Conexion.abrirConexion();
		if(conn !=null) {
			String SQL = "SELECT Precio FROM tablapizzas WHERE Nombre =?";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setString(1, nombrePizza);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					importe = result.getDouble("Precio");
				}
				Conexion.cerrarConexion();
				return importe;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Conexion.cerrarConexion();
		return importe;
	}
	
	public int insertarCabeceraPedido(String NombreCliente, String DireccionEntrega, 
			double Total, Date fecha) {
		int clave = -1;
		Connection conn = Conexion.abrirConexion();
		if(conn != null) {
			String SQL = "INSERT INTO tablapedido (Fecha, NombreCliente, Direccion, Total)"
					+ " VALUES (?,?,?,?)";
			try {
				PreparedStatement statement = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
				java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
				statement.setDate(1, sqlDate);
				statement.setString(2, NombreCliente);
				statement.setString(3, DireccionEntrega);
				statement.setDouble(4, Total);
				statement.executeUpdate();
				
				ResultSet claves = statement.getGeneratedKeys();
				claves.next();
				clave = claves.getInt(1);
				return clave;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}else {
			System.out.println("Error al abrir la bd");
		}
		
		return clave;
	}
	
	public void insertarLineaPedido(int idPedido, String NombrePizza, 
			int Cantidad, double importeLinea) {
		System.out.println("Intentando escribir en Linea: id: "+idPedido+" nombre:"+NombrePizza+" cantidad: "+Cantidad+" importe: "+importeLinea);
		Connection conn = Conexion.abrirConexion();
		if(conn !=null) {
			String SQL = "INSERT INTO tablalinea (idPedido,NombrePizza,Cantidad,ImporteLinea) "
					+ "VALUES (?,?,?,?)";
			try {
				PreparedStatement statement = conn.prepareCall(SQL);
				statement.setInt(1, idPedido);
				statement.setString(2, NombrePizza);
				statement.setInt(3, Cantidad);
				statement.setDouble(4, importeLinea);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}else {
			System.out.println("Error al abrir la bd");
		}
	}
	
	
}
