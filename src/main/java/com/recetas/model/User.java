package com.recetas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "username", length = 50, unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", length = 254, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 254)
	private String name;
	
	@Column(name = "last_name", length = 254)
	private String lastname;
	
	@Column(name = "sign_date")
	@Temporal(TemporalType.DATE)
	private Date signdate;
	
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Receta> recetas;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private List<Comentario> comentarios;

}
