package com.recetas.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Comentario;
import com.recetas.service.impl.ComentarioServiceImpl;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

	private final ComentarioServiceImpl comentarioServiceImpl;
	
	public ComentarioController(ComentarioServiceImpl comentarioServiceImpl) {
		this.comentarioServiceImpl=comentarioServiceImpl;
	}
	
	@PostMapping
	public Comentario addComentario(@RequestBody Comentario comentario) {
		return this.comentarioServiceImpl.addComentario(comentario);
	}
	
	@DeleteMapping("/{id}")
	public void deleteComentario(@PathVariable("id") Integer id) {
		this.comentarioServiceImpl.deleteComentario(id);
	}
}
