package com.coursemanager.domain.respository;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.repository.UsuarioRepositorio;
import com.coursemanager.util.usuario.UsuarioCreator;
import com.coursemanager.util.usuario.UsuarioTestComum;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes para o repositório de usuário")
class UsuarioRepositoryTest {
	
	@Autowired private UsuarioRepositorio usuarioRepositorio;

	@Test
	void cadastraUsuario_Sucesso() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		UsuarioEntidade usuarioOutput= this.usuarioRepositorio.save(usuario);
		UsuarioTestComum.testeSimplesUsuario(usuario, usuarioOutput);
	}
	
	@Test @DisplayName("cadastro de usuario com nome nulo")
	void cadastraUsuario_InSucesso_SemNome() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setNome(null);
		UsuarioTestComum.testeExcecaoCadastroUsuario("Nome não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("cadastro de usuario com nome em branco")
	void cadastraUsuario_InSucesso_NomeEmBranco() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setNome("     ");
		UsuarioTestComum.testeExcecaoCadastroUsuario("Nome não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("cadastro de usuario com e-mail nulo")
	void cadastraUsuario_InSucesso_SemEmail() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setEmail(null);
		UsuarioTestComum.testeExcecaoCadastroUsuario("E-mail não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("cadastro de usuario com e-mail em branco")
	void cadastraUsuario_InSucesso_EmailEmBranco() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setEmail("     ");
		UsuarioTestComum.testeExcecaoCadastroUsuario("E-mail não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("cadastro de usuario com senha nula")
	void cadastraUsuario_InSucesso_SemSenha() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setSenha(null);
		UsuarioTestComum.testeExcecaoCadastroUsuario("Senha não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("cadastro de usuario com senha em branco")
	void cadastraUsuario_InSucesso_SenhaEmBranco() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		usuario.setSenha("     ");
		UsuarioTestComum.testeExcecaoCadastroUsuario("Senha não deve estar em branco", usuarioRepositorio, usuario);
	}
	
	@Test @DisplayName("Alteração de usuário com sucesso")
	void alteraUsuarioSucesso() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		UsuarioEntidade usuarioSaved= this.usuarioRepositorio.save(usuario);
		usuarioSaved.setNome("novo nome");
		usuarioSaved.setEmail("novoemail@email.com");
		usuarioSaved.setSenha("nova senha");
		
		UsuarioEntidade usuarioUpdate= this.usuarioRepositorio.save(usuarioSaved);
		Assertions.assertThat(usuarioUpdate.getId_usuario()).isEqualTo(usuarioSaved.getId_usuario());
		UsuarioTestComum.testeSimplesUsuario(usuarioSaved, usuarioUpdate);
	}
	
	@Test @DisplayName("Deletar usuário com sucesso")
	void deleteUsuario_Sucesso() {
		UsuarioEntidade usuario= UsuarioCreator.criaUsuarioInput();
		UsuarioEntidade usuarioSaved= this.usuarioRepositorio.save(usuario);
		this.usuarioRepositorio.deleteById(usuarioSaved.getId_usuario());
		Optional<UsuarioEntidade> buscaUsuarioDeletado= this.usuarioRepositorio.findById(usuarioSaved.getId_usuario());
		Assertions.assertThat(buscaUsuarioDeletado.isEmpty()).isTrue();
	}
}
