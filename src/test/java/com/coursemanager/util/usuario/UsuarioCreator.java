package com.coursemanager.util.usuario;

import com.coursemanager.domain.model.UsuarioEntidade;

public class UsuarioCreator {
   public static UsuarioEntidade criaUsuarioInput() {
	   UsuarioEntidade usuarioEntidade= new UsuarioEntidade();
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
}
