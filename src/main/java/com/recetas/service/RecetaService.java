package com.recetas.service;

import java.util.List;

import com.recetas.model.Categoria;
import com.recetas.model.Comentario;
import com.recetas.model.Receta;

public interface RecetaService {

	List<Receta> getAllRecetas();
	
	Receta getReceta(Integer id);
	
	List<Receta> getRecetasBySearch(String search);
	
	List<Receta> getRecetasByCategory(Categoria categoria);
	
	Receta addReceta(Receta receta);
	
	Receta editReceta(Receta receta);
	
	void deleteReceta(Integer id);

	List<Comentario> getComentariosFromReceta(Integer id);
}
