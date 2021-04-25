package com.coursemanager.domain.dto;

import com.coursemanager.domain.model.ChaveMatriculaEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;

import lombok.Getter;

@Getter
public class MatriculaDTO {
	private ChaveMatriculaEntidade id;
	private UsuarioDTO aluno;
	private CursoDTO curso;
	boolean finalizado;
	
	public MatriculaDTO( MatriculaEntidade matriculaEntidade) {
		this.id= matriculaEntidade.getId();
		this.aluno= new UsuarioDTO(matriculaEntidade.getAluno());
		this.curso= new CursoDTO(matriculaEntidade.getCurso());
		this.finalizado= matriculaEntidade.isFinalizado();
	}
}
