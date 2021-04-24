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
@DisplayName("testes ao alterar curso na camada service")
public class AlterarCursoTeste {
	@InjectMocks
	private CursoService cursoService;
	@Mock
	private CursoRepositorio cursoRepositorio;
	
	private void setMocks(boolean existsByNome, CursoEntidade findByNome) {
		BDDMockito.when(this.cursoRepositorio.existsByNome(ArgumentMatchers.anyString()))
			.thenReturn(existsByNome);
		
		BDDMockito.when(this.cursoRepositorio.findByNome(ArgumentMatchers.anyString()))
			.thenReturn(findByNome);
	}
	
	@BeforeEach
	void setUp() {
		BDDMockito.when(this.cursoRepositorio.save(ArgumentMatchers.any()))
			.thenReturn(CursoCreator.cursoInput());
	}
	
	@Test @DisplayName("Alterando curso para um novo nome")
	void alteraCurso_Sucesso() {
		this.setMocks(false, null);
		CursoEntidade cursoOutput= this.cursoService.alteraCurso(CursoCreator.cursoInput());
		CursoTesteSimples.testeSimplesCurso(CursoCreator.cursoInput(), cursoOutput);
	}
	
	@Test @DisplayName("Alterando curso e mantendo o nome")
	void alteraCurso_Sucesso_MesmoNome() {
		this.setMocks(true, CursoCreator.cursoInput());
		CursoEntidade cursoOutput= this.cursoService.alteraCurso(CursoCreator.cursoInput());
		CursoTesteSimples.testeSimplesCurso(CursoCreator.cursoInput(), cursoOutput);
	}
	
	@Test @DisplayName("Alterando curso para um nome já existente no banco")
	void alteraCurso_InSucesso_NomeExistente() {
		this.setMocks(true, CursoCreator.cursoInput());
		
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		//altera o id para simular que eu estou tentando pegar o nome de outro curso existente no banco
		cursoInput.setId(100L); 
		
		Exception exception= assertThrows(
				CadastroException.class, 
				()-> cursoService.alteraCurso(cursoInput)
		);
		assertTrue(exception.getMessage().contains("Já existe um curso com esse nome, "
				+ "não foi possível fazer o cadastro! Por favor tente outro"));
	}
}
