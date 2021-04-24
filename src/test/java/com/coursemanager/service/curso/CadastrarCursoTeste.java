package com.coursemanager.service.curso;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.repository.CursoRepositorio;
import com.coursemanager.domain.service.CursoService;
import com.coursemanager.util.curso.CursoCreator;
import com.coursemanager.util.curso.CursoTesteSimples;

@ExtendWith(SpringExtension.class)
@DisplayName("teste de cadastro de curso na camada service")
public class CadastrarCursoTeste {
	@InjectMocks
	private CursoService cursoService;
	@Mock
	private CursoRepositorio cursoRepositorio;
	
	@BeforeEach
	void setUp() {
		BDDMockito.when(this.cursoRepositorio.save(ArgumentMatchers.any()))
			.thenReturn(CursoCreator.cursoInput());
		

	}
	
	@Test @DisplayName("Teste curso caso de sucesso")
	void testeCadastrarCurso_Sucesso() {
		BDDMockito.when(this.cursoRepositorio.existsByNome(ArgumentMatchers.anyString()))
			.thenReturn(false);
		CursoEntidade cursoOutput= this.cursoService.cadastraCurso(CursoCreator.cursoInput());
		CursoTesteSimples.testeSimplesCurso(CursoCreator.cursoInput(), cursoOutput);
	}
	
	@Test @DisplayName("cadastrando curso com nome existente")
	void testeCadastrarCurso_InSucesso_NomeExistente() {
		BDDMockito.when(this.cursoRepositorio.existsByNome(ArgumentMatchers.anyString()))
			.thenReturn(true);
		Exception exception= assertThrows(
				CadastroException.class, 
				()-> this.cursoService.cadastraCurso(CursoCreator.cursoInput())
		);
		assertTrue(exception.getMessage().contains("Já existe um curso com esse nome,"
				+ " não foi possível fazer o cadastro! Por favor tente outro"));
	}
}
