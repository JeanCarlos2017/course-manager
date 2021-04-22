package com.coursemanager.domain.respository;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.repository.CursoRepositorio;
import com.coursemanager.util.curso.CursoCreator;
import com.coursemanager.util.curso.CursoTesteSimples;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes para o repositório de curso")
class CursoRepositoryTest {
	@Autowired private CursoRepositorio cursoRepositorio;
	
	@Test @DisplayName("Cadastro de curso")
	void testeCadastroCurso_Sucesso() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		CursoEntidade cursoSaved= this.cursoRepositorio.save(cursoInput);
		CursoTesteSimples.testeSimplesCurso(cursoInput, cursoSaved);
		
	}
	
	@Test @DisplayName("Cadastro de curso sem nome")
	void testeCadastroCurso_InSucesso_SemNome() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setNome(null);
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("Nome não deve estar em branco"));		
	}
	
	@Test @DisplayName("Cadastro de curso com nome em branco")
	void testeCadastroCurso_InSucesso_NomeEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setNome("     ");
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("Nome não deve estar em branco"));	
	}
	
	@Test @DisplayName("Cadastro de curso sem código")
	void testeCadastroCurso_InSucesso_SemCodigo() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setCode(null);
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("código não pode ficar em branco "));		
	}
	
	@Test @DisplayName("Cadastro de curso com código em branco")
	void testeCadastroCurso_InSucesso_CodeEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setCode("     ");
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("código não pode ficar em branco"));	
	}
	
	@Test @DisplayName("Cadastro de curso sem descrição")
	void testeCadastroCurso_InSucesso_SemDescricao() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setDescription(null);
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("descrição não pode ficar em branco"));		
	}
	
	@Test @DisplayName("Cadastro de curso com Descrição em branco")
	void testeCadastroCurso_InSucesso_DescricaoEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setDescription("     ");
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("descrição não pode ficar em branco"));	
	}
	
	@Test @DisplayName("Cadastro de curso sem imagem")
	void testeCadastroCurso_InSucesso_SemImagem() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setImageUrl(null);
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("a imagem não pode ficar em branco"));		
	}
	
	@Test @DisplayName("Cadastro de curso com imagem em branco")
	void testeCadastroCurso_InSucesso_ImagemEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setImageUrl("     ");
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("a imagem não pode ficar em branco"));	
	}
	
	@Test @DisplayName("Cadastro de curso sem link de acesso")
	void testeCadastroCurso_InSucesso_SemLink() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setLink_de_acesso(null);
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("url de acesso ao curso não pode ficar em branco "));		
	}
	
	@Test @DisplayName("Cadastro de curso com link de acesso em branco")
	void testeCadastroCurso_InSucesso_LinkEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setLink_de_acesso("     ");
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> cursoRepositorio.save(cursoInput)
		);
		assertTrue(exception.getMessage().contains("url de acesso ao curso não pode ficar em branco "));	
	}
}
