package servidor;

//ESta es una clase básica en java que contiene los getters y los setters y un método de formateo para la salida por pantalla que es el toString
public class RequisitosPass {
	private int numCaractEspeciales;
	private int numDigitos;
	private int numMayusculas;
	private int numMinusculas;
	
	// Getters
		public int getNumCaractEspeciales() {
			return numCaractEspeciales;
		}

		public int getNumDigitos() {
			return numDigitos;
		}

		public int getNumMayusculas() {
			return numMayusculas;
		}

		public int getNumMinusculas() {
			return numMinusculas;
		}

		
	// Setters
		public void setNumCaractEspeciales(int numCaractEspeciales) {
			this.numCaractEspeciales = numCaractEspeciales;
		}

		public void setNumDigitos(int numDigitos) {
			this.numDigitos = numDigitos;
		}

		public void setNumMayusculas(int numMayusculas) {
			this.numMayusculas = numMayusculas;
		}

		public void setNumMinusculas(int numMinusculas) {
			this.numMinusculas = numMinusculas;
		}
		
		
	//Formateo mensaje
		@Override
		public String toString() {
			return "PasswordReqs{mayúsculas=" + numMayusculas + ", minúsculas=" + numMinusculas + ", dígitos="
					+ numDigitos + ", caracteresEspeciales=" + numCaractEspeciales + "}";
		}
}

