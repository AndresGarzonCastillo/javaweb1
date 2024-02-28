package co.edu.unbosque.exceptions;

public class ExcepcionesEstudiante extends Exception {
	public ExcepcionesEstudiante(String message) {
		super(message);
	}
	
	public static class ExcepcionEstudianteNoEncontrado extends ExcepcionesEstudiante {
		public ExcepcionEstudianteNoEncontrado(String message) {
			super(message);
		}
	}
	
	public static class ExcepcionEstudianteExistente extends ExcepcionesEstudiante {
		public ExcepcionEstudianteExistente(String message) {
			super(message);
		}
	}
}
