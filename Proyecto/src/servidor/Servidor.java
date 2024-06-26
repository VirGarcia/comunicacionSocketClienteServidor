package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private final int PUERTO = 4321; //especificado en las instrucciones de la PAC
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ServicioPass srvPass;

	
	//constructor
	public Servidor() throws IOException {
		//conexión
		serverSocket = new ServerSocket(PUERTO); 
		//cliente
		clientSocket = new Socket (); 
	}
	
	//Esta función es la que inicia la comunicación entre cliente y servidor
	public void start() throws IOException {
		while (true ) {
				System.out.println("Esperando al cliente Onliner.");
				//almacenamos la petición
				clientSocket = serverSocket.accept();
				//mostramos mensaje
				System.out.println("Onliner conectado desde " + clientSocket.getInetAddress());
				//el server está esperando a recibir peticiones
				
				//Inicio conexión
				DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
				//Enviamos sms al cliente
				toClient.writeUTF("Hola, soy un servidor. ¿Cómo te llamas?");
				//leemos del cliente
				String nameClient = fromClient.readUTF();
				System.out.println("Nombre del Onliner: " + nameClient);
				//fromCliente.readUTF(nameClient);
				toClient.writeUTF("Te doy la bienvenida, " + nameClient);
				//instrucciones para el usuario
				toClient.writeUTF("Voy a solicitarte distintos requisitos para la contraseña que voy a generar. "
						+ "\n¿Cuántas mayúsculas debe tener la contraseña?");
				
				RequisitosPass requisitosPass = new RequisitosPass();	
				//recogemos los requisitos deseados por el Onliner
				//y los establecemos
				//hacemos uso de los setters que están en RequisitosPass
				requisitosPass.setNumMayusculas(fromClient.readInt());
				
				toClient.writeUTF("¿Cúantas minúsculas debe tener la contraseña?");
				requisitosPass.setNumMinusculas(fromClient.readInt());
				
				toClient.writeUTF("¿Cúantos dígitos debe tener la contraseña?");
				requisitosPass.setNumDigitos(fromClient.readInt());
				
				toClient.writeUTF("¿Cúantos caracteres especiales debe tener la contraseña?");
				requisitosPass.setNumCaractEspeciales(fromClient.readInt());
				
				System.out.println("Los requisitos del cliente son los siguientes \n" + requisitosPass.toString());
				
				srvPass = new ServicioPass(requisitosPass);
				toClient.writeUTF("La longitud de la contraseña que se va a generar es de " + srvPass.longitudPass() + " caracteres.");
				System.out.println("Se ha enviado la longitud de la contraseña al cliente Onliner");
				
				
				toClient.writeUTF("¿Quieres generar una contraseña ahora? [si/no]");
				//según la elección del usuario el programa hace una opción u otra.
				String eleccion = fromClient.readUTF();
				if (eleccion.equalsIgnoreCase("si")) {
					System.out.println("Se ha enviado la contraseña al cliente");
					toClient.writeUTF("La contraseña generada es: " + srvPass.generaPass());

				} else {
					System.out.println("El cliente no desea generar una contraseña");
					toClient.writeUTF("No se generará ninguna contraseña. Hasta la próxima.");
				}
		}
	}
	
	/*public void stop() throws IOException {
		serverSocket.close();
	}*/

}


