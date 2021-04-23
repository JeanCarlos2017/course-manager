package com.coursemanager.util.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;

import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.UsuarioRepositorio;

public class UsuarioTestComum {

	public static void testeSimplesUsuario(UsuarioEntidade userInput, UsuarioEntidade userOutput) {
		Assertions.assertThat(userOutput).isNotNull();
		Assertions.assertThat(userOutput.getId_usuario()).isNotNull();
		Assertions.assertThat(userOutput.getNome()).isEqualTo(userInput.getNome());
		Assertions.assertThat(userOutput.getEmail()).isEqualTo(userInput.getEmail());
		Assertions.assertThat(userOutput.getSenha()).isEqualTo(userInput.getSenha());
	}
	
	public static void testeExcecaoCadastroUsuario(String mensagem, UsuarioRepositorio usuarioRepositorio, UsuarioEntidade usuario) {
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> usuarioRepositorio.save(usuario)
		);
		assertTrue(exception.getMessage().contains(mensagem));
	}
	
	public static void testeCadastroUsuarioService(UsuarioEntidade userInput, UsuarioEntidade userOutput) {
		Assertions.assertThat(userOutput).isNotNull();
		Assertions.assertThat(userOutput.getId_usuario()).isNotNull();
		Assertions.assertThat(userOutput.getNome()).isEqualTo(userInput.getNome());
		Assertions.assertThat(userOutput.getEmail()).isEqualTo(userInput.getEmail());
		//senhas diferentes por conta da criptografia
		Assertions.assertThat(userOutput.getSenha()).isNotEqualTo(userInput.getSenha());
	}
}
