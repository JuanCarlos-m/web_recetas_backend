package com.recetas.service;

import java.util.List;

import com.recetas.model.Categoria;
import com.recetas.model.Receta;

public interface RecetaService {

	List<Receta> getAllRecetas(Integer pageNo, Integer pageSize, String sortBy);
	
	Receta getReceta(Integer id);
	
	List<Receta> getRecetasBySearch(String search, Integer pageNo, Integer pageSize, String sortBy);
	
	List<Receta> getRecetasByCategory(Categoria categoria, Integer pageNo, Integer pageSize, String sortBy);
	
	Receta addReceta(Receta receta);
	
	Receta editReceta(Receta receta);
	
	void deleteReceta(Integer id);

	List<Receta> getRecetasByUser(String username, Integer pageNo, Integer pageSize, String sortBy);

}
