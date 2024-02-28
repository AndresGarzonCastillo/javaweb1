package co.edu.unbosque.controller;

import co.edu.unbosque.exceptions.ExcepcionesEstudiante.ExcepcionEstudianteExistente;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado;

public class Main {
	public static void main(String[] args) throws ExcepcionEstudianteExistente, ExcepcionEstudianteNoEncontrado {
		Controller controller = new Controller();
		controller.inicio();
	}
}
