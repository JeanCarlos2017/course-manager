package com.coursemanager.domain.dto;

import com.coursemanager.domain.model.UsuarioEntidade;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class UsuarioDTO {
	private long id_usuario;

	private String email;

	private String senha;

	private String nome;
	
	
	public UsuarioDTO( UsuarioEntidade usuario) {
		this.id_usuario= usuario.getId_usuario();
		this.email= usuario.getEmail();
		this.senha= usuario.getSenha();
		this.nome= usuario.getNome();
	}
}
