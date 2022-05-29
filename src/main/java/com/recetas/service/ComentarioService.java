package com.recetas.service;

import java.util.List;

import com.recetas.dto.PagedResponse;
import com.recetas.model.Comentario;

public interface ComentarioService {
	
	PagedResponse getComentariosFromReceta(Integer id, Integer pageNo, Integer pageSize, String sortBy);
	
	Comentario addComentario(Comentario comentario);
	
	void deleteComentario(Integer id);

}
