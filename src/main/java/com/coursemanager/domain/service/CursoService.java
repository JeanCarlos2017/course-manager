package com.coursemanager.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.repository.CursoRepositorio;

@Service
public class CursoService {
	@Autowired
	private CursoRepositorio cursoRepositorio;

	
	
	public List <CursoEntidade> listarCursos(){
		return this.cursoRepositorio.findAll();
	}
	
	public CursoEntidade buscaPorId(long id){
		Optional<CursoEntidade> curso= this.cursoRepositorio.findById(id);
		if(curso.isEmpty()) throw new EntidadeNaoEncontradaException("Não existe um curso que tenha esse id em nossa base de dados!");
		else return curso.get();
	}
	
	public CursoEntidade cadastraCurso(CursoEntidade curso) {
		if(this.cursoRepositorio.existsByNome(curso.getNome())) {
			throw new CadastroException
			("Já existe um curso com esse nome, não foi possível fazer o cadastro! Por favor tente outro");
		}
		
		return this.cursoRepositorio.save(curso);
	}
	
	public CursoEntidade alteraCurso(CursoEntidade curso) {
		if(this.cursoRepositorio.existsByNome(curso.getNome())) {
			CursoEntidade busca= this.cursoRepositorio.findByNome(curso.getNome());
			if(curso.getId() != busca.getId()) {
				throw new CadastroException
				("Já existe um curso com esse nome, não foi possível fazer o cadastro! Por favor tente outro");
			}
		}
		return this.cursoRepositorio.save(curso);
	}

	public Collection<MatriculaEntidade> getMatricula(long id_curso) {
		CursoEntidade curso= this.buscaPorId(id_curso);
		return curso.getAlunos();
	}
	
	public Collection<CursoEntidade> buscaCursosQueContemCom(String nome){
		return this.cursoRepositorio.findAllByNomeContainingIgnoreCase(nome);
	}
}
