package com.coursemanager.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanager.domain.model.ChaveMatriculaEntidade;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;

public interface MatriculaRepositorio extends JpaRepository<MatriculaEntidade, Long> {
		List<UsuarioEntidade> findByAluno(UsuarioEntidade aluno);
		List<CursoEntidade> findByCurso(CursoEntidade curso);
		boolean existsById(ChaveMatriculaEntidade chave);
	
}
