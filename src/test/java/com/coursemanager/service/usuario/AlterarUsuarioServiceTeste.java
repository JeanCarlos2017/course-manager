package com.coursemanager.service.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.UsuarioRepositorio;
import com.coursemanager.domain.service.UsuarioService;
import com.coursemanager.util.usuario.UsuarioCreator;
import com.coursemanager.util.usuario.UsuarioTestComum;

@ExtendWith(SpringExtension.class)
@DisplayName("testes ao alterar usuário camada service")
public class AlterarUsuarioServiceTeste {
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
	
	private void  testeExcecaoUpdateUsuarioService(String mensagem, long idUsuario) {
		//para o idInput ser diferente o idOuput e falhar na verificação do service
		this.usuarioInput.setId_usuario(idUsuario);
		Exception exception= assertThrows(
				CadastroException.class, 
				()-> usuarioService.alteraUsuario(this.usuarioInput)
		);
		assertTrue(exception.getMessage().contains(mensagem));
	}
	
	void setMocksUpdateUsuario(Optional<UsuarioEntidade>findByNome, Optional<UsuarioEntidade> findByEmail) {
		BDDMockito.when(usuarioRepositorio.findByNome(ArgumentMatchers.anyString()))
		  .thenReturn(findByNome);
		
		BDDMockito.when(usuarioRepositorio.findByEmail(ArgumentMatchers.anyString()))
		  .thenReturn(findByEmail);
	}
	
	@Test @DisplayName("teste de sucesso, nome e email novo")
	void testeAlteraUsuario_Sucesso() throws Exception {
		setMocksUpdateUsuario(Optional.empty(), Optional.empty());
		UsuarioEntidade usuarioOutput= this.usuarioService.alteraUsuario(this.usuarioInput);
		UsuarioTestComum.testeCadastroUsuarioService(usuarioInput, usuarioOutput);		
	}
	
	@Test @DisplayName("alterando o usuário e mantendo o e-mail")
	void testeAlteraUsuario_Sucesso_MesmoEmail() throws Exception {
		this.setMocksUpdateUsuario(Optional.empty(), Optional.of(UsuarioCreator.criaUsuarioOutputService()));
		
		UsuarioEntidade usuarioOutput= this.usuarioService.alteraUsuario(this.usuarioInput);
		UsuarioTestComum.testeCadastroUsuarioService(usuarioInput, usuarioOutput);		
	}
	
	@Test @DisplayName("alterando o usuário e mantendo o nome")
	void testeAlteraUsuario_Sucesso_MesmoNome() throws Exception {
	    this.setMocksUpdateUsuario(Optional.of(UsuarioCreator.criaUsuarioOutputService()), Optional.empty());
		
		UsuarioEntidade usuarioOutput= this.usuarioService.alteraUsuario(this.usuarioInput);
		UsuarioTestComum.testeCadastroUsuarioService(usuarioInput, usuarioOutput);		
	}
	
	@Test @DisplayName("alterando o usuário e mantendo o nome e email")
	void testeAlteraUsuario_InSucesso_MesmoNomeEEmail() throws Exception {
		this.setMocksUpdateUsuario(Optional.of(UsuarioCreator.criaUsuarioOutputService()), 
				Optional.of(UsuarioCreator.criaUsuarioOutputService()));
				
		UsuarioEntidade usuarioOutput= this.usuarioService.alteraUsuario(this.usuarioInput);
		UsuarioTestComum.testeCadastroUsuarioService(usuarioInput, usuarioOutput);		
	}
	
	@Test @DisplayName("alterando o nome usuário para um existente no banco")
	void testeAlteraUsuario_InSucesso_NomeExistente() throws Exception {
		this.setMocksUpdateUsuario(Optional.of(UsuarioCreator.criaUsuarioOutputService()), Optional.empty());
		this.testeExcecaoUpdateUsuarioService("nome de usuário já existente, por favor tente outro!", 11L);	
	}
	
	@Test @DisplayName("alterando o e-mail do usuário para um existente no banco")
	void testeAlteraUsuario_InSucesso_EmailExistente() throws Exception {
		this.setMocksUpdateUsuario(Optional.empty(), Optional.of(UsuarioCreator.criaUsuarioOutputService()));
		this.testeExcecaoUpdateUsuarioService("E-mail já existente, por favor tente outro!", 11L);	
	}


}
