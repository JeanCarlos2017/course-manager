package com.coursemanager.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class UsuarioLogin {
	private long id;
	
	private String email;
	
	private String nome;
	
	private String token;
	
	private String senha;

	public UsuarioLogin(UsuarioEntidade usuario, String token) {
		this.id= usuario.getId_usuario();
		this.email= usuario.getEmail();
		this.senha= usuario.getSenha();
		this.token= token;
	}
	
}
