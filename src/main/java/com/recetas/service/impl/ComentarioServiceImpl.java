package com.recetas.service.impl;

import org.springframework.stereotype.Service;

import com.recetas.model.Comentario;
import com.recetas.repository.ComentarioRepository;
import com.recetas.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	private final ComentarioRepository comentarioRepository;
	
	public ComentarioServiceImpl (ComentarioRepository comentarioRepository) {
		this.comentarioRepository=comentarioRepository;
	}
	
	@Override
	public Comentario addComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		return this.comentarioRepository.save(comentario);
	}

	@Override
	public void deleteComentario(Integer id) {
		this.comentarioRepository.deleteById(id);
	}

}
