package com.recetas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "valoraciones")
@Getter
@Setter
public class Valoracion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private Usuarios user;
	
	@ManyToOne()
	@JoinColumn(name = "receta_id")
	private Receta receta;
	
	@Column(name = "valoracion", nullable = false)
	private Integer valoracion;
	
}
