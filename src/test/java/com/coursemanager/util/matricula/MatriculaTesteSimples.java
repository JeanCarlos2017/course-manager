package com.coursemanager.util.matricula;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.model.ChaveMatriculaEntidade;
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.service.MatriculaService;

public class MatriculaTesteSimples {
	
	public static void testaMatricula(MatriculaEntidade matricula, CursoEntidade curso, UsuarioEntidade usuario) {
		ChaveMatriculaEntidade chave= MatriculaCreator.buildChaveMatricula(usuario.getId_usuario(), curso.getId());
		Assertions.assertThat(matricula.getId()).isNotNull();
		Assertions.assertThat(matricula.getId()).isEqualTo(chave);
		Assertions.assertThat(matricula.getAluno()).isEqualTo(usuario);
		Assertions.assertThat(matricula.getCurso()).isEqualTo(curso);
		
	}
	
	public static void testeMatriculaException(String mensagem, MatriculaService matriculaService) {
		Exception exception= assertThrows(
				CadastroException.class, 
				()-> matriculaService.matriculaAluno(-1L, 1L)
		);
		assertTrue(exception.getMessage().contains(mensagem));
	}

}
