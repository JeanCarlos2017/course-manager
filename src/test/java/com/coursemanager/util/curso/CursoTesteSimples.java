package com.coursemanager.util.curso;

import org.assertj.core.api.Assertions;

import com.coursemanager.domain.model.CursoEntidade;

public class CursoTesteSimples {
	public static void testeSimplesCurso(CursoEntidade cursoInput, CursoEntidade cursoOutput) {
		Assertions.assertThat(cursoOutput.getId()).isNotNull();
		Assertions.assertThat(cursoOutput.getCode()).isEqualTo(cursoInput.getCode());
		Assertions.assertThat(cursoOutput.getDescription()).isEqualTo(cursoInput.getDescription());
		Assertions.assertThat(cursoOutput.getDuration()).isEqualTo(cursoInput.getDuration());
		Assertions.assertThat(cursoOutput.getImageUrl()).isEqualTo(cursoInput.getImageUrl());
		Assertions.assertThat(cursoOutput.getLink_de_acesso()).isEqualTo(cursoInput.getLink_de_acesso());
		Assertions.assertThat(cursoOutput.getNome()).isEqualTo(cursoInput.getNome());
		Assertions.assertThat(cursoOutput.getPrice()).isEqualTo(cursoInput.getPrice());
		Assertions.assertThat(cursoOutput.getRating()).isEqualTo(cursoInput.getRating());
	}
	
	
	public void testeExcecaoCursoAoCadastrar(String messagem) {
		
	}
}
