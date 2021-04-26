package com.coursemanager.service.matricula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.MatriculaRepositorio;
import com.coursemanager.domain.service.CursoService;
import com.coursemanager.domain.service.MatriculaService;
import com.coursemanager.domain.service.UsuarioService;
import com.coursemanager.util.curso.CursoCreator;
import com.coursemanager.util.matricula.MatriculaCreator;
import com.coursemanager.util.matricula.MatriculaTesteSimples;
import com.coursemanager.util.usuario.UsuarioCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("testes de matrícula na camada service")
public class MatriculaServiceTeste {
	@InjectMocks
	private MatriculaService matriculaService;
	@Mock 
	private UsuarioService usuarioService;
	@Mock 
	private CursoService cursoService;
	@Mock 
	private MatriculaRepositorio matriculaRepositorio;
	
	private CursoEntidade cursoInput= CursoCreator.cursoInput();
	private UsuarioEntidade usuarioInput= UsuarioCreator.criaUsuarioInput();
	
	
	@Test @DisplayName("Adicionando matricula caso de sucesso")
	void addMatricula_Sucesso() throws Exception {
		this.setMocksGetById(usuarioInput, cursoInput);
		MatriculaEntidade matricula= MatriculaCreator.buildMatricula(usuarioInput, cursoInput);
		this.setMockSaveMatricula(matricula);
		this.matriculaService.matriculaAluno(1L, 1L);
		MatriculaTesteSimples.testaMatricula(matricula, cursoInput, usuarioInput);
		
	}
	
	@Test @DisplayName("Adicionando matricula para um id de aluno não existente")
	void addMatricula_InSucesso_IdAlunoNaoEncontrado() throws Exception {
		this.setMocksGetById(null, cursoInput);
		MatriculaTesteSimples.testeMatriculaException("O id de aluno está incorreto, por favor verifique",
				matriculaService);
	}
	
	@Test @DisplayName("Adicionando matricula para um id de curso não existente")
	void addMatricula_InSucesso_IdCursoNaoEncontrado() throws Exception {
		this.setMocksGetById(usuarioInput, null);
		MatriculaTesteSimples.testeMatriculaException("O id de curso está incorreto, por favor verifique",
				matriculaService);
	}
	
	@Test @DisplayName("Adicionando matricula para um id de curso e aluno não existente")
	void addMatricula_InSucesso_IdCursoEAlunoNaoEncontrado() throws Exception {
		this.setMocksGetById(null, null);
		MatriculaTesteSimples.testeMatriculaException("O id de aluno está incorreto, por favor verifique",
				matriculaService);
	}
	
	private void setMocksGetById(UsuarioEntidade getUsuario, CursoEntidade getCurso) {
		BDDMockito.when(this.usuarioService.getById(ArgumentMatchers.anyLong()))
			.thenReturn(getUsuario);
		BDDMockito.when(this.cursoService.buscaPorId(ArgumentMatchers.anyLong()))
			.thenReturn(getCurso);
	}
	
	private void setMockSaveMatricula(MatriculaEntidade matriculaEntidade) {
		BDDMockito.when(this.matriculaRepositorio.save(ArgumentMatchers.any()))
					.thenReturn(matriculaEntidade);
	}
}
