package com.recetas.dto;

import java.util.Arrays;
import java.util.List;

import com.recetas.model.Comentario;
import com.recetas.model.Receta;
import com.recetas.model.Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagedResponse {

	private List<Usuarios> usuarios;
	private List<Receta> recetas;
	private List<Comentario> comentarios;
	
	private long totalElements;
	private Integer size;
	private Integer number;
	

	public PagedResponse(List<Usuarios> usuarios, List<Receta> recetas, List<Comentario> comentarios, long totalElements, Integer number, Integer size) {
		super();
		this.usuarios = usuarios;
		this.recetas=recetas;
		this.comentarios=comentarios;
		this.totalElements = totalElements;
		this.size = size;
		this.number = number;
	}
	
}
