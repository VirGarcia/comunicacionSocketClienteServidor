package cliente;

import java.io.IOException;

public class MainCliente {

	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		
		//Esta es el método que arranca la parte del cliente
		//Creamos el objeto de cliente
		Cliente cli = new Cliente();
		//Iniciamos la conexión
		cli.interactua();

	}

}
