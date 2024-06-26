package servidor;

//Esta clase es la que contiene la lógica para generar la contraseña según las especificaciones del usuario y la longitud
//utilizamos un método importado Random donde marcamos un rango y dentro de él se genera aleatoriamente lo que indiquemos.
import java.util.Random;

public class ServicioPass {
	private RequisitosPass requisitosPass;
	
	public ServicioPass(RequisitosPass requisitosPass) {
		this.requisitosPass = requisitosPass;
	}
	
	public String generaPass() {
		
		StringBuilder passw = new StringBuilder();	
		//mayúsculas
		for (int i = 0; i < requisitosPass.getNumMayusculas(); i++) {
			passw.append(addRandom('A', 'Z'));
		}
		//minúsculas
		for (int j = 0; j < requisitosPass.getNumMinusculas(); j++) {
					passw.append(addRandom('a', 'z'));
		}

		//dígitos
		for (int k = 0; k < requisitosPass.getNumDigitos(); k++) {
			passw.append(addRandom('0', '9'));
		}
		
		//carac especiales
		String posiblesEsp = "!@#$%^&*()_-+=.:?";
		for (int l = 0; l < requisitosPass.getNumCaractEspeciales(); l++) {
			passw.append(posiblesEsp.charAt(new Random().nextInt(posiblesEsp.length())));
		}
		
		return passw.toString();
	}
	
	//método para calcular la longitud de la contraseña
	public int longitudPass() {
		return requisitosPass.getNumMayusculas() + requisitosPass.getNumMinusculas()
		+ requisitosPass.getNumDigitos() + requisitosPass.getNumCaractEspeciales();
	}
	
	//método que adiciona de manera aleatoria.
	private char addRandom(char primera, char ultima) {
		return (char) (primera + new Random().nextInt(ultima - primera + 1));
	}
	
}