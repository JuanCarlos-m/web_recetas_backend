package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.dto.ComentarioPostDTO;
import com.recetas.model.Comentario;
import com.recetas.service.ComentarioService;

@RestController
@CrossOrigin(origins = "*")
public class ComentarioController {

	private final ComentarioService comentarioService;
	
	public ComentarioController(ComentarioService comentarioService) {
		this.comentarioService=comentarioService;
	}
	
	@GetMapping("/recetas/{id}/comentarios")
	public List<Comentario> getComentariosByReceta (@PathVariable("id") Integer id, @RequestParam(name = "no", defaultValue = "0") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "created_At") String sortBy){
		return this.comentarioService.getComentariosFromReceta(id, pageNo,pageSize,sortBy);
	}
	
	@PostMapping("/comentario")
	public Comentario addComentario(@RequestBody ComentarioPostDTO comentarioPost) {
		Comentario comentario=new Comentario();
		comentario.setTexto(comentarioPost.getTexto());
		comentario.setAutor(comentarioPost.getAutor());
		comentario.setReceta(comentarioPost.getReceta());
		return this.comentarioService.addComentario(comentario);
	}
	
	@DeleteMapping("/comentario/{id}")
	public void deleteComentario(@PathVariable("id") Integer id) {
		this.comentarioService.deleteComentario(id);
	}
}
