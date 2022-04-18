package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Categoria;
import com.recetas.model.Receta;
import com.recetas.service.RecetaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recetas")
public class RecetaController {
	
	private final RecetaService recetaService;
	
	public RecetaController(RecetaService recetaService) {
		this.recetaService=recetaService;
	}
	
	@GetMapping
	public List<Receta> getAllRecetas(@RequestParam(name = "no", defaultValue = "0") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "createdAt") String sortBy){
		return this.recetaService.getAllRecetas(pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/user/{username}")
	public List<Receta> getRecetasByUser (@PathVariable("username") String username, @RequestParam(name = "no", defaultValue = "0") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "created_At") String sortBy) {
		return this.recetaService.getRecetasByUser(username, pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/{id}")
	public Receta getReceta (@PathVariable("id") Integer id) {
		return this.recetaService.getReceta(id);
	}
	
	@GetMapping("/")
	public List<Receta> getRecetasBySearch(@RequestParam(name = "search") String search,@RequestParam(name = "no", defaultValue = "0") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "created_At") String sortBy){
		return this.recetaService.getRecetasBySearch(search, pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/categoria/{categoria}")
	public List<Receta> getRecetasByCategoria(@PathVariable("categoria") String categoria, @RequestParam(name = "no", defaultValue = "0") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "createdAt") String sortBy){
		return this.recetaService.getRecetasByCategory(Categoria.valueOf(categoria), pageNo,pageSize,sortBy);
	}
	
	@PostMapping
	public Receta addReceta(@RequestBody Receta receta) {
		return this.recetaService.addReceta(receta);
	}
	
	@PutMapping()
	public Receta editReceta(@RequestBody Receta receta) {
		return this.recetaService.editReceta(receta);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReceta(@PathVariable("id") Integer id) {
		this.recetaService.deleteReceta(id);
	}
	

}
