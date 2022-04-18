package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Valoracion;
import com.recetas.service.ValoracionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/valoracion")
public class ValoracionController {
	
	public final ValoracionService valoracionService;
	
	public ValoracionController(ValoracionService valoracionService) {
		this.valoracionService=valoracionService;
	}
	
	@GetMapping("/{recetaid}")
	public List<Valoracion> getValoracionFromReceta(@PathVariable("recetaid")Integer recetaid) {
		return this.valoracionService.getValoracionesFromReceta(recetaid);
	}
	
	@GetMapping("/{recetaid}/{userid}")
	public Valoracion getValoracionFromUsuario(@PathVariable("recetaid")Integer recetaid, @PathVariable("userid")String userid) {
		return this.valoracionService.getValoracionFromUser(recetaid, userid);
	}
	
	@PostMapping
	public Valoracion addValoracion(@RequestBody Valoracion valoracion) {
		return this.valoracionService.addValoracion(valoracion);
	}
	
	@PutMapping
	public Valoracion editValoracion(@RequestBody Valoracion valoracion) {
		return this.valoracionService.editValoracion(valoracion);
	}
}
