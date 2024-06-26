package servidor;

import java.io.IOException;

//Esta es la clase principal del paquete servidor y es la primera que se tiene que arrancar si queremos que funcione la aplicación
//el servidor siempre tiene que estar activo si no esta activo no entran peticiones, por eso el metodo stop esta creado pero comentado
public class MainServidor {
	public static void main(String[] args) throws IOException {
		//Definimos objeto
		Servidor server = new Servidor();
		System.out.println("Servidor arrancado...");
		
		//Iniciamos el servidor
		server.start();
		
		//finalizamos el servicio
		//server.stop();
	}

}
