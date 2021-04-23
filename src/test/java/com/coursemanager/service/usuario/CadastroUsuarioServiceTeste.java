package com.coursemanager.service.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.UsuarioRepositorio;
import com.coursemanager.domain.service.UsuarioService;
import com.coursemanager.util.usuario.UsuarioCreator;
import com.coursemanager.util.usuario.UsuarioTestComum;

@ExtendWith(SpringExtension.class)
public class CadastroUsuarioServiceTeste {
	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	
	private UsuarioEntidade usuarioInput= UsuarioCreator.criaUsuarioInput();
	
	@BeforeEach
	void setUp() {
		BDDMockito.when(usuarioRepositorio.save(ArgumentMatchers.any()))
			.thenReturn(UsuarioCreator.criaUsuarioOutputService());
	}
	@Test @DisplayName("teste de sucesso para cadastro usuário")
	void testeCadastroUsuario_Sucesso() throws Exception {
		UsuarioEntidade usuarioOutput= this.usuarioService.cadastraUsuario(usuarioInput);
		UsuarioTestComum.testeCadastroUsuarioService(usuarioInput, usuarioOutput);
	}
}
