package com.coursemanager.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable @NoArgsConstructor @EqualsAndHashCode @Getter @Setter @AllArgsConstructor
public class ChaveMatriculaEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "aluno_id")
    private Long alunoId;

    @Column(name = "curso_id")
    private Long cursoId;
	
}
