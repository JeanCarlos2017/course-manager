package com.coursemanager.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="tb_usuario")
@NoArgsConstructor
public class UsuarioEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private long id_usuario;
	
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	@Getter @Setter private String email;
	
	@NotBlank(message = "{senha.not.blank}")
	@Getter @Setter private String senha;
	
	@NotBlank(message = "{name.not.blank}")
	@Getter @Setter private String nome;
		
	



}
