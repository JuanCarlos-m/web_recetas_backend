package com.recetas.model;

import lombok.Getter;
import lombok.Setter;

/* Estas importaciones y el codigo abajo ya no se usa, pero esta presente por si fuera necesario, se borrara para la versión final
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;*/
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Persistable extends Auditable {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false, length = 100)
    private String id;*/

    private Boolean active = true;

}
