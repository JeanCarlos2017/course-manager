package com.coursemanager.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="tb_curso")
@NoArgsConstructor
public class CursoEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private long id;
	
	@NotBlank(message = "{name.not.blank}")
	@Getter @Setter private String nome;
	
	@NotBlank(message = "{image.not.blank}")
	@Getter @Setter private String imageUrl;
	
	@NotNull(message = "{price.not.null}")
	@Getter @Setter private float price;
	
	@NotBlank(message = "{code.not.blank}")
	@Getter @Setter private String code;
	
	@NotNull(message = "{duration.not.blank}")
	@Getter @Setter private float duration;
	
	@NotNull(message = "{rating.not.null}")
	@Getter @Setter private short rating;
    
	@NotNull @Temporal(TemporalType.TIMESTAMP)
    @Getter private Date releaseDate= new java.sql.Date(System.currentTimeMillis());
    
	@NotBlank(message = "{description.not.blank}")
    @Getter @Setter private String description;
    
	@NotBlank(message = "{url.not.blank}")
    @Getter @Setter private String link_de_acesso;
    
	@Getter @Setter private boolean concluido= false;
    
    @ManyToMany(mappedBy= "lista_de_cursos", fetch= FetchType.LAZY)
	@JsonIgnoreProperties("lista_de_cursos")
    @Getter @Setter private Set<UsuarioEntidade> lista_aluno= new HashSet<>();
    
    
    public void addAluno(UsuarioEntidade usuario) {
    	this.lista_aluno.add(usuario);
    }
}
