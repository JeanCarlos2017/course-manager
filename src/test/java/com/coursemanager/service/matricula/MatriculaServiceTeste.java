package com.coursemanager.service.matricula;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.service.CursoService;
import com.coursemanager.domain.service.MatriculaService;
import com.coursemanager.domain.service.UsuarioService;
import com.coursemanager.util.curso.CursoCreator;
import com.coursemanager.util.curso.CursoTesteSimples;
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
	
	private CursoEntidade cursoInput= CursoCreator.cursoInput();
	private UsuarioEntidade usuarioInput= UsuarioCreator.criaUsuarioInput();

	
	
	private void setMocksGetById(UsuarioEntidade getUsuario, CursoEntidade getCurso) {
		BDDMockito.when(this.usuarioService.getById(ArgumentMatchers.anyLong()))
			.thenReturn(getUsuario);
		BDDMockito.when(this.cursoService.buscaPorId(ArgumentMatchers.anyLong()))
			.thenReturn(getCurso);
	}
	
	private void setMocksAlteraEntidade(UsuarioEntidade usuarioUpdated, CursoEntidade cursoUpdated) throws Exception {
		usuarioUpdated.addCurso(cursoUpdated);
		cursoUpdated.addAluno(usuarioUpdated);
		
		BDDMockito.when(this.usuarioService.alteraUsuario(ArgumentMatchers.any()))
			.thenReturn(usuarioUpdated);
		BDDMockito.when(this.cursoService.alteraCurso(ArgumentMatchers.any()))
		 	.thenReturn(cursoUpdated);
	}
	
	void testaMatricula(CursoEntidade cursoInput, CursoEntidade cursoUpdated) {
		CursoTesteSimples.testeSimplesCurso(cursoInput, cursoUpdated);
		Assertions.assertThat(cursoUpdated.getLista_aluno().size()).isEqualTo(1);
	}
	
	@Test @DisplayName("Adicionando matricula caso de sucesso")
	void addMatricula_Sucesso() throws Exception {
		this.setMocksGetById(usuarioInput, cursoInput);
		this.setMocksAlteraEntidade(usuarioInput, cursoInput);
		
		CursoEntidade cursoUpdated= this.matriculaService.matriculaAluno(1L, 1L);
		this.testaMatricula(cursoInput, cursoUpdated);
	}
	
	@Test @DisplayName("Adicionando matricula para um id de aluno não existente")
	void addMatricula_InSucesso_IdAlunoNaoEncontrado() throws Exception {
		this.setMocksGetById(null, cursoInput);
		
		Exception exception= assertThrows(
				EntidadeNaoEncontradaException.class, 
				()-> matriculaService.matriculaAluno(-1L, 1L)
		);
		assertTrue(exception.getMessage().contains("Curso ou Usuário não existente, por favor verifique isso!"));
	}
	
	@Test @DisplayName("Adicionando matricula para um id de curso não existente")
	void addMatricula_InSucesso_IdCursoNaoEncontrado() throws Exception {
		this.setMocksGetById(usuarioInput, null);
		
		Exception exception= assertThrows(
				EntidadeNaoEncontradaException.class, 
				()-> matriculaService.matriculaAluno(-1L, 1L)
		);
		assertTrue(exception.getMessage().contains("Curso ou Usuário não existente, por favor verifique isso!"));
	}
	
	@Test @DisplayName("Adicionando matricula para um id de curso e aluno não existente")
	void addMatricula_InSucesso_IdCursoEAlunoNaoEncontrado() throws Exception {
		this.setMocksGetById(null, null);
		
		Exception exception= assertThrows(
				EntidadeNaoEncontradaException.class, 
				()-> matriculaService.matriculaAluno(-1L, 1L)
		);
		assertTrue(exception.getMessage().contains("Curso ou Usuário não existente, por favor verifique isso!"));
	}
}
