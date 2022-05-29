package com.recetas.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;
    

}
