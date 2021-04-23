package com.coursemanager.service.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.model.UsuarioLogin;
import com.coursemanager.domain.repository.UsuarioRepositorio;
import com.coursemanager.domain.service.UsuarioService;
import com.coursemanager.util.usuario.UsuarioCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("testes ao logar na camada service")
public class LogarUsuarioServiceTeste {
	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	private UsuarioLogin usuarioInput;
	
	private void testeUsuarioLogin(UsuarioLogin userInput,UsuarioLogin userOutput) {
		Assertions.assertThat(userOutput).isNotNull();
		Assertions.assertThat(userOutput.getToken()).isNotBlank();
		Assertions.assertThat(userOutput.getNome()).isEqualTo(userInput.getNome());
		Assertions.assertThat(userOutput.getEmail()).isEqualTo(userInput.getEmail());
	}
	
	private void  testeExcecaoLoginUsuarioService(String mensagem, UsuarioLogin userInput) {
		Exception exception= assertThrows(
				EntidadeNaoEncontradaException.class, 
				()-> usuarioService.login(userInput)
		);
		assertTrue(exception.getMessage().contains(mensagem));
	}
	
	void setFindByNome(Optional<UsuarioEntidade> usuario) {
		BDDMockito.when(usuarioRepositorio.findByNome(ArgumentMatchers.anyString()))
			.thenReturn(usuario);
	}
	
	@BeforeEach
	void setUsarioInput() {
		this.usuarioInput= UsuarioCreator.criaUsuarioLoginInput();
	}
	
	@Test @DisplayName("logar usuário com sucesso")
	void testeUsuario_Sucesso() {
		this.setFindByNome(Optional.of(UsuarioCreator.criaUsuarioOutputService()));
		UsuarioLogin usuarioOutput= usuarioService.login(this.usuarioInput);
		this.testeUsuarioLogin(usuarioInput, usuarioOutput);
	}
	
	@Test @DisplayName("logar usuário com senha errada")
	void testeUsuario_InSucesso_SenhaErrada() {
		this.setFindByNome(Optional.of(UsuarioCreator.criaUsuarioOutputService()));
		this.usuarioInput.setSenha("senha errada");
		this.testeExcecaoLoginUsuarioService("Senha incorreta", this.usuarioInput);
	}
	
	@Test @DisplayName("logar usuário com nome errado")
	void testeUsuario_InSucesso_NomeErrado() {
		this.setFindByNome(Optional.empty());
		this.testeExcecaoLoginUsuarioService("Nome de usuário não encontrado, "
				+ "por favor verifique se o mesmo está correto", this.usuarioInput);
	}
	
}
