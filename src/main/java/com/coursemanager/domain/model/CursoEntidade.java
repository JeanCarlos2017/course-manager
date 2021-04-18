package com.coursemanager.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity @Table(name="tb_curso")
@NoArgsConstructor
public class CursoEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private long id;
	@NotBlank(message = "{name.not.blank}")
	@Getter @Setter private String nome;
	@NotBlank(message = "{image.not.blank}")
	@Getter @Setter private String imageUrl;
	@NotBlank(message = "{price.not.blank}")
	@Getter @Setter private float price;
	@NotBlank(message = "{code.not.blank}")
	@Getter @Setter private String code;
	@NotBlank(message = "{duration.not.blank}")
	@Getter @Setter private int duration;
	@NotBlank(message = "{rating.not.blank}")
	@Getter @Setter private short rating;
    @NotNull @Temporal(TemporalType.TIMESTAMP)
    @Getter private Date releaseDate= new java.sql.Date(System.currentTimeMillis());
    @NotBlank(message = "{description.not.blank}")
    @Getter @Setter private String description;

}
