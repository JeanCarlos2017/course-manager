package com.coursemanager.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.CursoRepositorio;

@Service
public class CursoService {
	@Autowired
	private CursoRepositorio cursoRepositorio;
	@Autowired
	private UsuarioService usuarioService;
	
	private UsuarioEntidade buscaUsuarioPorId(long id) {
		UsuarioEntidade usuarioEntidade= this.usuarioService.getById(id);
		if( usuarioEntidade== null) {
			throw new CadastroException("Usuário não encontrado, não foi possível realizar o cadastro do curso");
		}else return usuarioEntidade;
	}
	
	
	public List <CursoEntidade> listarCursos(){
		return this.cursoRepositorio.findAll();
	}
	
	public Optional<CursoEntidade> buscaPorId(long id){
		return this.cursoRepositorio.findById(id);
	}
	
	public CursoEntidade cadastraCurso(CursoEntidade curso) {
		if(this.cursoRepositorio.existsByNome(curso.getNome()).isPresent()) {
			throw new CadastroException
			("Já existe um curso com esse nome, não foi possível fazer o cadastro! Por favor tente outro");
		}
		
		return this.cursoRepositorio.save(curso);
	}
}
