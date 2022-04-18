package com.recetas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.recetas.model.Categoria;
import com.recetas.model.Receta;
import com.recetas.dao.RecetaRepository;
import com.recetas.service.RecetaService;



@Service
public class RecetaServiceImpl implements RecetaService {

	private final RecetaRepository recetaRepository;
	
	public RecetaServiceImpl(RecetaRepository recetaRepository) {
		this.recetaRepository=recetaRepository;
	}
	
	@Override
	public List<Receta> getAllRecetas(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Receta> pagedResult=this.recetaRepository.findAll(paging);
		
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Receta>();
		}
		//return this.recetaRepository.findAll();
	}

	@Override
	public Receta getReceta(Integer id) {
		return this.recetaRepository.findById(id).get();
	}

	@Override
	public List<Receta> getRecetasBySearch(String search, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Receta> pagedResult=this.recetaRepository.findBySearch(search,paging);
		
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Receta>();
		}
		
		//return this.recetaRepository.findBySearch(search);
	}

	@Override
	public List<Receta> getRecetasByCategory(Categoria categoria, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Receta> pagedResult=this.recetaRepository.findAllByCategoria(categoria, paging);
		
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Receta>();
		}
		
		//return this.recetaRepository.findAllByCategoria(categoria);
	}

	@Override
	public Receta addReceta(Receta receta) {
		receta.setCreatedAt(new Date());
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
	public List<Receta> getRecetasByUser(String username, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Receta> pagedResult=this.recetaRepository.findAllByUsername(username, paging);
		
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Receta>();
		}
		
	}

}
