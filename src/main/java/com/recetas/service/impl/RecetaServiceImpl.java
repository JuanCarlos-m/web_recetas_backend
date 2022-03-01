package com.recetas.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Categoria;
import com.recetas.model.Comentario;
import com.recetas.model.Receta;
import com.recetas.repository.RecetaRepository;
import com.recetas.service.RecetaService;

@Service
public class RecetaServiceImpl implements RecetaService {

	private final RecetaRepository recetaRepository;
	
	public RecetaServiceImpl(RecetaRepository recetaRepository) {
		this.recetaRepository=recetaRepository;
	}
	
	@Override
	public List<Receta> getAllRecetas() {
		return this.recetaRepository.findAll();
	}

	@Override
	public Receta getReceta(Integer id) {
		return this.recetaRepository.findById(id).get();
	}

	@Override
	public List<Receta> getRecetasBySearch(String search) {
		return this.recetaRepository.findBySearch(search);
	}

	@Override
	public List<Receta> getRecetasByCategory(Categoria categoria) {
		return this.recetaRepository.findAllByCategoria(categoria);
	}

	@Override
	public Receta addReceta(Receta receta) {
		receta.setFechacreacion(new Date());
		return this.recetaRepository.save(receta);
	}

	@Override
	public Receta editReceta(Receta receta) {
		this.recetaRepository.findById(receta.getId()).orElseThrow();
		return this.recetaRepository.save(receta);
	}

	@Override
	public void deleteReceta(Integer id) {
		this.recetaRepository.findById(id).orElseThrow();
		this.recetaRepository.deleteById(id);

	}

	@Override
	public List<Comentario> getComentariosFromReceta(Integer id) {
		return this.recetaRepository.findById(id).get().getComentarios();
	}
	

}
