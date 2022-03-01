package com.recetas.service;

import com.recetas.model.Comentario;

public interface ComentarioService {
	
	Comentario addComentario(Comentario comentario);
	
	void deleteComentario(Integer id);

}
