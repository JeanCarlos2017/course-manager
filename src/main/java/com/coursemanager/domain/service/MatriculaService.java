package com.coursemanager.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.model.ChaveMatriculaEntidade;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.MatriculaRepositorio;

@Service
public class MatriculaService {
	@Autowired	private MatriculaRepositorio matriculaRepositorio;
	@Autowired private UsuarioService usuarioService;
	@Autowired private CursoService cursoService;
	
	
	private CursoEntidade curso;
	private UsuarioEntidade aluno;
	private ChaveMatriculaEntidade chaveMatriculaEntidade;
	private MatriculaEntidade matriculaEntidade;
	
	public MatriculaEntidade matriculaAluno(long idAluno, long idCurso) {
		this.buscaAluno(idAluno);
		this.buscaCurso(idCurso);
		this.buildChaveMatricula();
		this.buildMatricula();
		return this.saveMatricula();
	}

	public List<MatriculaEntidade> listaMatricula() {
		return this.matriculaRepositorio.findAll();
	}
	
	private void buscaAluno( long idAluno) {
		this.aluno= this.usuarioService.getById(idAluno);
		if(this.aluno == null) throw new CadastroException("O id de aluno está incorreto, por favor verifique");
	}
	
	private void buscaCurso(long idCurso) {
		this.curso= this.cursoService.buscaPorId(idCurso);
		if(this.curso == null) throw new CadastroException("O id de curso está incorreto, por favor verifique");
	}
	
	private void buildChaveMatricula() {
		this.chaveMatriculaEntidade= new ChaveMatriculaEntidade(this.aluno.getId_usuario(), this.curso.getId());
	}
	
	private void buildMatricula() {
		this.matriculaEntidade= new MatriculaEntidade(this.chaveMatriculaEntidade, this.aluno, this.curso, false);
	}
	
	private MatriculaEntidade saveMatricula() {
		return this.matriculaRepositorio.save(this.matriculaEntidade);
	}
}
