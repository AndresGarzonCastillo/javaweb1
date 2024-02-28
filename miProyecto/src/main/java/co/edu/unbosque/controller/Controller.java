package co.edu.unbosque.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante.ExcepcionEstudianteExistente;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado;
import co.edu.unbosque.model.EstudianteDAO;
import co.edu.unbosque.model.EstudianteDAOMemoria;
import co.edu.unbosque.model.EstudianteDTO;

public class Controller {
	private EstudianteDAO dao = new EstudianteDAOMemoria();
	private Scanner sc = new Scanner(System.in);

	public void inicio() throws ExcepcionEstudianteExistente, ExcepcionEstudianteNoEncontrado {
		int elegir;
		do {
			System.out.println("1. Almacenar nuevo estudiante");
			System.out.println("2. Buscar un estudiante");
			System.out.println("3. Actualizar el darto de un estudiante");
			System.out.println("4. Eliminar un estudiante");
			System.out.println("5. Ver todos los estudiantes");
			System.out.println("6. Salir");
			elegir = sc.nextInt();
			sc.nextLine();

			switch (elegir) {
			case 1:
				almacenarEstudiante();
				break;
			case 2:
				obtenerEstudiante();
				break;
			case 3:
				actualizarEstudiante();
				break;
			case 4:
				eliminarEstudiante();
				break;
			case 5:
				todosLosEstudiantes();
				break;
			case 6:
				System.out.println("Saliendo...");
				break;
			}
		} while (elegir != 6);
	}
	
	private boolean correoValido(String correo) {
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    return pattern.matcher(correo).matches();
	}

	private void almacenarEstudiante() throws ExcepcionEstudianteExistente {
		System.out.println("Datos del Estudiante");
		System.out.println("Numero de identificacion: ");
		String id = sc.nextLine();
		System.out.println("Nombres: ");
		String nombres = sc.nextLine();
		System.out.println("Apellidos: ");
		String apellidos = sc.nextLine();
		int edad;
		do {
			System.out.println("Edad: ");
			while (!sc.hasNextInt()) {
				System.out.println("Ingrese una edad numerica");
				System.out.println("Edad: ");
				sc.nextLine();
			}
			edad = sc.nextInt();
			sc.nextLine();
		} while (edad<0); 
		String correo;
		do {
			System.out.println("Correo electronico: ");
			correo = sc.nextLine();
			if(!correoValido(correo)) {
				System.out.println("Ingresar un correo valido");
			}
		} while (!correoValido(correo));
		EstudianteDTO estudiante = new EstudianteDTO(id, nombres, apellidos, correo, edad);
		dao.almacenar(estudiante);
		System.out.println("Estudiante Almacenado");
		System.out.println(estudiante);
	}

	private void obtenerEstudiante() throws ExcepcionEstudianteNoEncontrado {
		System.out.println("Ingrese numero de identificacion: ");
		String id = sc.nextLine();
		EstudianteDTO estudiante = dao.obtener(id);
		System.out.println("Datos del Estudiante");
		System.out.println("Numero de identificacion: " + estudiante.getId());
		System.out.println("Nombres: " + estudiante.getNombres());
		System.out.println("Apellidos: " + estudiante.getApellidos());
		System.out.println("Edad: " + estudiante.getEdad());
		System.out.println("Correo electronico: " + estudiante.getCorreo());
	}

	private void actualizarEstudiante() throws ExcepcionEstudianteNoEncontrado {
		System.out.println("Ingrese numero de identificacion: ");
		String id = sc.nextLine();
		EstudianteDTO estudianteExistente = dao.obtener(id);
		System.out.println("Ingrese los nuevos Datos del Estudiante");
		System.out.println("Correo electronico: ");
		String correo = sc.nextLine();
		EstudianteDTO estudienteActualizado = new EstudianteDTO(id, estudianteExistente.getNombres(),
				estudianteExistente.getApellidos(), correo, estudianteExistente.getEdad());
		dao.actualizar(estudienteActualizado);
		System.out.println("Estudiante actualizado");
		System.out.println(estudienteActualizado);
	}

	private void eliminarEstudiante() throws ExcepcionEstudianteNoEncontrado {
		System.out.println("Ingrese numero de identificacion: ");
		String id = sc.nextLine();
		dao.eliminar(id);
		System.out.println("Estudiante eliminado");
	}

	private void todosLosEstudiantes() {
		List<EstudianteDTO> estudiantes = dao.todosEstudiantes();
		if (!estudiantes.isEmpty()) {
			System.out.println("Lista de Estudientes");
			for (EstudianteDTO estudiante : estudiantes) {
				System.out.println("Numero de identificacion: " + estudiante.getId());
				System.out.println("Nombres: " + estudiante.getNombres());
				System.out.println("Apellidos: " + estudiante.getApellidos());
				System.out.println("Edad: " + estudiante.getEdad());
				System.out.println("Correo electronico: " + estudiante.getCorreo());
				System.out.println("");
			}
		} else {
			System.out.println("No hay estudiantes almacenados");
		}
	}
}
