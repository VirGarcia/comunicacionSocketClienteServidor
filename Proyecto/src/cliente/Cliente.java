package cliente;
/**
 * @author Vir Garcia
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private final String HOST = "localhost";
	private final int PUERTO = 4321;
	private Socket serverSocket;
	
	public Cliente () throws IOException {
		serverSocket = new Socket (HOST, PUERTO);
	}
	
	public void interactua() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		String nameClient  = "";
		String nameClientFormat ="";
		int upper = 0;
		int upperAbs = 0;
		int lower = 0;
		int lowerAbs = 0;
		int digits = 0;
		int digitsAbs = 0;
		int specialCharac = 0;
		int specialCharacAbs = 0;
		String eleccion = "";
		boolean eleccionContenido = false;
		
		
	// entrada informacion
		DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream());
		//mostramos info
		System.out.println(fromServer.readUTF()); 
		
	//envio informacion
		DataOutputStream toServer = new DataOutputStream(serverSocket.getOutputStream());
		//recogemos info
		nameClient = sc.next();
		//guardamos en nueva variable el nombre del Onliner pero con la primera letra en mayúscula, tambien advertimos si hay algun digito
		nameClientFormat = empiezaMayuscula(nameClient) + "\n";

		//mandamos al server el nombre del cliente con la mayúscula incorporada
		toServer.writeUTF(nameClientFormat);
		//mensaje bienvenida
		System.out.println(fromServer.readUTF());
		
		//preguntamos los requisitos al usuario
		System.out.println(fromServer.readUTF());
		//leemos mayúsculas
		upper = sc.nextInt();
		//preparamos nueva variable para almacenar el dígito sin signo negativo ni positivo, en caso de que el usuario lo meta
		//esto lo hacemos siempre que introduce el usuario un digito, asi nos aseguramos de que en el servidor se escribe correctamente
		upperAbs = Math.abs(upper);
		toServer.writeInt(upperAbs);
		
		//minúsculas
		System.out.println(fromServer.readUTF());
		lower = sc.nextInt();
		lowerAbs = Math.abs(lower);
		toServer.writeInt(lowerAbs);
		
		//dígitos	
		System.out.println(fromServer.readUTF());
		digits = sc.nextInt();
		digitsAbs = Math.abs(digits);
		toServer.writeInt(digitsAbs);
		
		//caracteres especiales
		System.out.println(fromServer.readUTF());
		specialCharac = sc.nextInt();
		specialCharacAbs = Math.abs(specialCharac);
		toServer.writeInt(specialCharacAbs);
		
		System.out.println(fromServer.readUTF());
		System.out.println(fromServer.readUTF());
		
		
		while (!eleccionContenido) {
			// entrada por consola
			eleccion = sc.next();

			//control de la elección del usuario, también le mandamos mensaje si introduce otro valor
			if (eleccion.equalsIgnoreCase("si") || eleccion.equalsIgnoreCase("no")) {
				eleccionContenido = true;
			} else {
				System.out.println("Onliner dime si o no");
			}
		}
		// envio info al server
		toServer.writeUTF(eleccion);

		// recibimos del server la info
		System.out.println(fromServer.readUTF());


	toServer.close();
	fromServer.close();
	serverSocket.close();
	
	}
	
	//Formateo nombre
	/*
	 * Esta función lo que hace es transformarle la primera letra del nombre del usuario a mayúscula
	 * también aprovecha para verificar si el nombre que se introduce es vacío, nulo o si tiene dígitos
	 * en tal caso le manda al usuario un aviso
	 */
	public String empiezaMayuscula(String texto) {
	    if (texto == null || texto.isEmpty() || existeNumeros(texto)) {
	    	String aviso = "Onliner pero tu nombre no puede estar vacío, nulo o contener números.";
	        //return texto != null && !texto.isEmpty() ? texto : aviso;
	        //return texto; // Devolver el texto original si es nulo o vacío
	    	return aviso + "\nHas introducido: " + texto;
	    }
	    return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}
	
	/*
	 * Esta función es para verificar si existe números dentro del nombre y la usa empiezaMayuscula
	 */
	public boolean existeNumeros(String texto) {
	    for (char c : texto.toCharArray()) {
	        if (Character.isDigit(c)) {
	            return true; // true si hay digitos
	        }
	    }
	    return false; // false si no hay digitos
	}

}
