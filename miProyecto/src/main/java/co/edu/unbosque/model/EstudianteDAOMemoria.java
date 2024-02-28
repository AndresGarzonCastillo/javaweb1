package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import co.edu.unbosque.exceptions.ExcepcionesEstudiante;

public class EstudianteDAOMemoria implements EstudianteDAO {
	private HashMap<String, EstudianteDTO> db = new HashMap<>();
	@Override
	public void almacenar(EstudianteDTO estudiante) throws ExcepcionesEstudiante.ExcepcionEstudianteExistente {
		if(db.containsKey(estudiante.getId())) {
			throw new ExcepcionesEstudiante.ExcepcionEstudianteExistente("Estudiante con ID " + estudiante.getId()+ " ya existe.");
		}
		db.put(estudiante.getId(), estudiante);
	}
	@Override
	public EstudianteDTO obtener(String id) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado {
		EstudianteDTO estudiante = db.get(id);
		if(estudiante==null) {
			throw new ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado("Estudiante con ID " + id + " no fue encontrado");
		}
		return estudiante;
	}
	@Override
	public void actualizar(EstudianteDTO estudiante) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado {
		if(!db.containsKey(estudiante.getId())) {
			throw new ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado("Estudiante con ID " + estudiante.getId() + " no fue encontrado");
		}
		db.put(estudiante.getId(), estudiante);
	}
	@Override
	public void eliminar(String id) throws ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado {
		if(db.remove(id)==null) {
			throw new ExcepcionesEstudiante.ExcepcionEstudianteNoEncontrado("Estudiante con ID " + id + " no fue encontrado");
		}
	}
	@Override
	public List<EstudianteDTO> todosEstudiantes(){
		return new ArrayList<>(db.values());
	}
}
