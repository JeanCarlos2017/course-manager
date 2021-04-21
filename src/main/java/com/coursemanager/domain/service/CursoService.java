package com.coursemanager.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
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
	
	public CursoEntidade matriculaAluno(long id_usuario, long id_curso) {
		UsuarioEntidade usuario= this.buscaUsuarioPorId(id_usuario);
		CursoEntidade curso= this.buscaPorId(id_curso);
		//relação usuário-curso
		curso.addAluno(usuario);
		this.usuarioService.addMatriculaCurso(id_usuario, curso);
		return this.cursoRepositorio.save(curso);
	}
	
	public Set<CursoEntidade> cursosMatriculadosPorAluno(long id_usuario){
		UsuarioEntidade usuario= this.buscaUsuarioPorId(id_usuario);
		return usuario.getLista_de_cursos();
	}
}
