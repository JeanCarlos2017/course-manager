package com.coursemanager.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor @Getter @Setter @AllArgsConstructor
public class MatriculaEntidade {
	@EmbeddedId
	private ChaveMatriculaEntidade id;
	
	 @ManyToOne  @MapsId("alunoId") @JoinColumn(name = "aluno_id")
	 @JsonIgnoreProperties("aluno")
	 private UsuarioEntidade aluno;
	 
	 @ManyToOne  @MapsId("cursoId") @JoinColumn(name = "curso_id")
	 @JsonIgnoreProperties("curso")
	 private CursoEntidade curso;
	 
	 boolean finalizado;
	
}
