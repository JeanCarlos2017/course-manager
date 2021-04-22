package com.coursemanager.util.usuario;

import org.assertj.core.api.Assertions;

import com.coursemanager.domain.model.UsuarioEntidade;

public class UsuarioTestComum {

	public static void testeSimplesUsuario(UsuarioEntidade userInput, UsuarioEntidade userOutput) {
		Assertions.assertThat(userOutput).isNotNull();
		Assertions.assertThat(userOutput.getId_usuario()).isNotNull();
		Assertions.assertThat(userOutput.getNome()).isEqualTo(userInput.getNome());
		Assertions.assertThat(userOutput.getEmail()).isEqualTo(userInput.getEmail());
		Assertions.assertThat(userOutput.getSenha()).isEqualTo(userInput.getSenha());
	}
	

}
