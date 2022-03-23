package com.recetas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios extends Persistable {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public Usuarios(String username, String password) {
        this.username = username;
        this.password = password;
    }


	@Column(name = "first_name", length = 254)
	private String name;
	
	@Column(name = "last_name", length = 254)
	private String lastname;
    
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


}
