package com.coursemanager.util.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.coursemanager.domain.model.UsuarioEntidade;

public class UsuarioCreator {
   public static UsuarioEntidade criaUsuarioInput() {
	   UsuarioEntidade usuarioEntidade= new UsuarioEntidade();
	   usuarioEntidade.setId_usuario(1L);
	   usuarioEntidade.setNome("nome teste");
	   usuarioEntidade.setSenha("senha teste");
	   usuarioEntidade.setEmail("teste@teste.com");
	   
	   return usuarioEntidade;
   }
   
   
   public static UsuarioEntidade criaUsuarioOutputRepositorio() {
	   UsuarioEntidade usuarioEntidade= new UsuarioEntidade();
	   usuarioEntidade.setId_usuario(1L);
	   usuarioEntidade.setNome("nome teste");
	   usuarioEntidade.setSenha("senha teste");
	   usuarioEntidade.setEmail("teste@teste.com");
	   
	   return usuarioEntidade;
   }
   
   public static UsuarioEntidade criaUsuarioOutputService() {
	   UsuarioEntidade usuarioEntidade= new UsuarioEntidade();
	   usuarioEntidade.setId_usuario(1L);
	   usuarioEntidade.setNome("nome teste");
	   usuarioEntidade.setEmail("teste@teste.com");
	   usuarioEntidade.setSenha("senha teste");
	   usuarioEntidade.setSenha(criptografaSenhaUsuario(usuarioEntidade.getSenha()));
	   return usuarioEntidade;
   }
   
	public static String criptografaSenhaUsuario(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
}
