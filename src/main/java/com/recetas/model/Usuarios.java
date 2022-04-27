package com.recetas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios extends Persistable {

	@Id
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    @JsonIgnore //No queremos recibir la contraseña del usuario cuando hacemos peticion GET
    private String password;

    public Usuarios(String username, String password) {
        this.username = username;
        this.password = password;
    }



	public Usuarios(String username, String password, String name, String lastname, Date fechanac) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.fechanac = fechanac;
	}



	@Column(name = "first_name", length = 254)
	private String name;
	
	@Column(name = "last_name", length = 254)
	private String lastname;
	
	@Column(name = "fecha_nac")
	@Temporal(TemporalType.DATE)
	private Date fechanac;
    
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Receta> recetas;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private List<Comentario> comentarios;
	
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Roles> roles = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "follows",
    		joinColumns = @JoinColumn(name="seguidor"),
    		inverseJoinColumns = @JoinColumn(name = "seguido")
    		)
    @JsonIgnore
    private List<Usuarios> follows;


}
