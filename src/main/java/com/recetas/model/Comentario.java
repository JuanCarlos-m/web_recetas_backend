package com.recetas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comentario")
	private int id;
	
	@Column(name = "titulo", length = 120)
	private String titulo;
	
	@Column(name = "texto", columnDefinition = "TEXT")
	private String texto;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fechacreacion;
	
	@ManyToOne
	private User autor;
	
	@ManyToOne
	private Receta receta;
}
