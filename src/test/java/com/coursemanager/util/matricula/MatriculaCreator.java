package com.coursemanager.util.matricula;

import com.coursemanager.domain.model.ChaveMatriculaEntidade;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;

public class MatriculaCreator {
	public static ChaveMatriculaEntidade buildChaveMatricula(long idAluno, long idCurso) {
		return new ChaveMatriculaEntidade(idAluno, idCurso);
	}
	
	public static MatriculaEntidade buildMatricula( UsuarioEntidade aluno, CursoEntidade curso) {
		ChaveMatriculaEntidade chave= buildChaveMatricula(aluno.getId_usuario(), curso.getId());
		return new MatriculaEntidade(chave, aluno, curso, false);
	}
}
