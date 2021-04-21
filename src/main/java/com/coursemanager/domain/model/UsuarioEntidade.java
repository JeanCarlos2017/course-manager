package com.coursemanager.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
public class UsuarioEntidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private long id_usuario;

	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	@Getter
	@Setter
	private String email;

	@NotBlank(message = "{senha.not.blank}")
	@Getter
	@Setter
	private String senha;

	@NotBlank(message = "{name.not.blank}")
	@Getter
	@Setter
	private String nome;

	// Relação Tema-Postagem
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_matriculado_curso", 
	  joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id"))
	@JsonIgnoreProperties("lista_aluno")
	@Getter @Setter private Set<CursoEntidade> lista_de_cursos = new HashSet<>();
	
	
	public void addCurso(CursoEntidade curso) {
		this.lista_de_cursos.add(curso);
	}
}
