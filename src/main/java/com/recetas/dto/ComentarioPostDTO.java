package com.recetas.dto;

import com.recetas.model.Receta;
import com.recetas.model.Usuarios;

import lombok.Data;

@Data
public class ComentarioPostDTO {

	private String texto;
	private Usuarios autor;
	private Receta receta;
}
