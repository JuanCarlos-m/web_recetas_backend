package com.recetas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recetas")
@Getter
@Setter
public class Receta extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_receta")
	private int id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "contenido", columnDefinition = "TEXT")
	private String contenido;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "Categoría")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate//Borrar, Auditable ya hace esto, habra que modificar la funcion en el servicio tambien
	private Date fechacreacion;
	
	@ManyToOne
	private Usuarios user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receta")
	private List<Comentario> comentarios;
	
}
