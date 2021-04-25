package com.coursemanager.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.coursemanager.domain.model.MatriculaEntidade;

public class UtilsDTO {
	public static List<MatriculaDTO> matriculaEntidadeToDTO(List<MatriculaEntidade> matriculaEntidadeList){
		List<MatriculaDTO> listaMatriculaDTO= new ArrayList<MatriculaDTO>();
		for(MatriculaEntidade matriculaEntidade: matriculaEntidadeList) {
			listaMatriculaDTO.add(new MatriculaDTO(matriculaEntidade));
		}
		return listaMatriculaDTO;
	}
}
