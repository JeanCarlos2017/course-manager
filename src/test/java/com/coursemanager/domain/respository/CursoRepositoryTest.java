package com.coursemanager.domain.respository;

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
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "Nome não deve estar em branco", cursoRepositorio);	
	}
	
	@Test @DisplayName("Cadastro de curso com nome em branco")
	void testeCadastroCurso_InSucesso_NomeEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setNome("     ");
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "Nome não deve estar em branco", cursoRepositorio);	
	}
	
	@Test @DisplayName("Cadastro de curso sem código")
	void testeCadastroCurso_InSucesso_SemCodigo() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setCode(null);
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "código não pode ficar em branco ", cursoRepositorio);	
	}
	
	@Test @DisplayName("Cadastro de curso com código em branco")
	void testeCadastroCurso_InSucesso_CodeEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setCode("     ");
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "código não pode ficar em branco ", cursoRepositorio);		
	}
	
	@Test @DisplayName("Cadastro de curso sem descrição")
	void testeCadastroCurso_InSucesso_SemDescricao() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setDescription(null);
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "descrição não pode ficar em branco", cursoRepositorio);		
	}
	
	@Test @DisplayName("Cadastro de curso com Descrição em branco")
	void testeCadastroCurso_InSucesso_DescricaoEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setDescription("     ");
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "descrição não pode ficar em branco", cursoRepositorio);		
	}
	
	@Test @DisplayName("Cadastro de curso sem imagem")
	void testeCadastroCurso_InSucesso_SemImagem() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setImageUrl(null);
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "a imagem não pode ficar em branco", cursoRepositorio);	
		}
	
	@Test @DisplayName("Cadastro de curso com imagem em branco")
	void testeCadastroCurso_InSucesso_ImagemEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setImageUrl("     ");
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "a imagem não pode ficar em branco", cursoRepositorio);		
	}
	
	@Test @DisplayName("Cadastro de curso sem link de acesso")
	void testeCadastroCurso_InSucesso_SemLink() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setLink_de_acesso(null);
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "url de acesso ao curso não pode ficar em branco ", cursoRepositorio);	
		}
	
	@Test @DisplayName("Cadastro de curso com link de acesso em branco")
	void testeCadastroCurso_InSucesso_LinkEmBranco() {
		CursoEntidade cursoInput= CursoCreator.cursoInput();
		cursoInput.setLink_de_acesso("     ");
		CursoTesteSimples.testeExcecaoCursoAoCadastrar(cursoInput, "url de acesso ao curso não pode ficar em branco ", cursoRepositorio);
	}
}
