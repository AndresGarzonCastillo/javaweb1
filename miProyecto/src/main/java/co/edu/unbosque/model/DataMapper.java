package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {
	public static EstudianteDTO mapToDTO(Estudiante model) {
		EstudianteDTO dto = new EstudianteDTO();
		dto.setId(model.getId());
		dto.setNombres(model.getNombres());
		dto.setApellidos(model.getApellidos());
		dto.setEdad(model.getEdad());
		dto.setCorreo(model.getCorreo());
		return dto;
	}
	
	public static Estudiante mapToModel(EstudianteDTO dto) {
		Estudiante model = new Estudiante();
		model.setId(dto.getId());
		model.setNombres(dto.getNombres());
		model.setApellidos(dto.getApellidos());
		model.setEdad(dto.getEdad());
		model.setCorreo(dto.getCorreo());
		return model;
	}
	
	public static List<EstudianteDTO> mapListToDTO(List<Estudiante> modelList){
		List<EstudianteDTO> dtoList = new ArrayList<>();
		for(Estudiante model : modelList) {
			dtoList.add(mapToDTO(model));
		}
		return dtoList;
	}
}
