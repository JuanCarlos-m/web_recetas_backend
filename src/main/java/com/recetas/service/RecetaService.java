package com.recetas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.recetas.dto.PagedResponse;
import com.recetas.model.Categoria;
import com.recetas.model.Receta;

public interface RecetaService {

	PagedResponse getAllRecetas(Integer pageNo, Integer pageSize, String sortBy);

	Receta getReceta(Integer id);
	
	PagedResponse getRecetasBySearch(String search, Integer pageNo, Integer pageSize, String sortBy);
	
	PagedResponse getRecetasByCategory(Categoria categoria, Integer pageNo, Integer pageSize, String sortBy);
	
	Receta addReceta(Receta receta);
	
	Receta editReceta(Receta receta);
	
	void deleteReceta(Integer id);

	PagedResponse getRecetasByUser(String username, Integer pageNo, Integer pageSize, String sortBy);

	PagedResponse getTimeline(String username, Integer pageNo, Integer pageSize, String sortBy);
}
