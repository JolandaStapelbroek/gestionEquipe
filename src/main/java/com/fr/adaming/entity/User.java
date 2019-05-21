package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class User {
	
	//Rappel : ID en type non primary obligatoire (Integer et non int)
	//Association unidirectionelle
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Annotations Swagger
	@ApiModelProperty()
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String pwd;
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team equipe;

}
