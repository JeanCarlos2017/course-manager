package com.coursemanager.domain.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "tb_usuario")
@NoArgsConstructor @Getter @Setter @ToString
public class UsuarioEntidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_usuario;

	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	private String email;

	@NotBlank(message = "{senha.not.blank}")
	private String senha;

	@NotBlank(message = "{name.not.blank}")
	private String nome;
	
	@OneToMany(mappedBy = "aluno")
	private Set<MatriculaEntidade> cursos;
}
