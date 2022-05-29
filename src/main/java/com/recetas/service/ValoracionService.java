package com.recetas.service;

import java.util.List;

import com.recetas.model.Valoracion;

public interface ValoracionService {

	//List<Valoracion> getValoracionesFromReceta(Integer id);
	
	Long getValoracionesFromReceta(Integer id);
	
	Valoracion getValoracionFromUser(Integer recetaid, String usuarioid);
	
	Valoracion addValoracion(Valoracion valoracion);
	
	Valoracion editValoracion(Valoracion valoracion);

	void deleteValoracion(Integer id);
}
