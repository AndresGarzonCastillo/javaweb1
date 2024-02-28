package co.edu.unbosque.model;

import java.util.List;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante;

public interface EstudianteDAO {
	void almacenar(EstudianteDTO estudiante) throws ExcepcionesEstudiante.ExcepcionEstudianteExistente;
	EstudianteDTO obtener(String id) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado;
	void actualizar(EstudianteDTO estudiante) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado;
	void eliminar(String id) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado;
	List<EstudianteDTO> todosEstudiantes();
}
