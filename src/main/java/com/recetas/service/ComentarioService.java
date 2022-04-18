package com.recetas.service;

import java.util.List;

import com.recetas.model.Comentario;

public interface ComentarioService {
	
	List<Comentario> getComentariosFromReceta(Integer id, Integer pageNo, Integer pageSize, String sortBy);
	
	Comentario addComentario(Comentario comentario);
	
	void deleteComentario(Integer id);

}
