package com.coursemanager.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @Getter @Setter @ToString
public class UsuarioLogin {
	private long id;
	
	private String email;
	
	@NotBlank(message = "{name.not.blank}")
	private String nome;
	
	private String token;
	
	@NotBlank(message = "{senha.not.blank}")
	private String senha;

	public UsuarioLogin(UsuarioEntidade usuario, String token) {
		this.id= usuario.getId_usuario();
		this.email= usuario.getEmail();
		this.senha= usuario.getSenha();
		this.nome= usuario.getNome();
		this.token= token;
	}
	
}
