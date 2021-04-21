package com.coursemanager.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;

@Service
public class MatrículaService {
	@Autowired
	private CursoService cursoService;
	@Autowired
	private UsuarioService usuarioService;
	
	public CursoEntidade matriculaAluno(long id_usuario, long id_curso) {
		UsuarioEntidade usuario= usuarioService.getById(id_usuario);
		CursoEntidade curso= cursoService.buscaPorId(id_curso);
		//relação usuário-curso
		curso.addAluno(usuario);
		usuario.addCurso(curso);
		try {
			this.usuarioService.alteraUsuario(usuario, id_usuario);
		} catch (Exception e) {
			System.out.println(e);
		}
		return this.cursoService.alteraCurso(curso);
	}
	
	public Set<CursoEntidade> cursosMatriculadosPorAluno(long id_usuario){
		UsuarioEntidade usuario= this.usuarioService.getById(id_usuario);
		return usuario.getLista_de_cursos();
	}
}
