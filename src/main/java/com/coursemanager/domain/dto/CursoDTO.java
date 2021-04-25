package com.coursemanager.domain.dto;

import java.util.Date;

import com.coursemanager.domain.model.CursoEntidade;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CursoDTO {
	private long id;
	
	private String nome;
	
	private String imageUrl;
	
	private float price;
	
	private String code;
	
	private float duration;
	
	private short rating;
    
	private Date releaseDate;
    
	private String description;
    
	private String link_de_acesso;
    
	private boolean concluido;
	
	public CursoDTO( CursoEntidade curso) {
		this.id= curso.getId();
		this.nome= curso.getNome();
		this.imageUrl= curso.getImageUrl();
		this.price= curso.getPrice();
		this.code= curso.getCode();
		this.duration= curso.getDuration();
		this.rating= curso.getRating();
		this.releaseDate= curso.getReleaseDate();
		this.description= curso.getDescription();
		this.link_de_acesso= curso.getLink_de_acesso();
		this.concluido= curso.isConcluido();
	}
}
