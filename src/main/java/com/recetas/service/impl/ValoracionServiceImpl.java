package com.recetas.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.recetas.model.Valoracion;
import com.recetas.dao.ValoracionRepository;
import com.recetas.service.ValoracionService;

@Service
public class ValoracionServiceImpl implements ValoracionService {

	public final ValoracionRepository valoracionRepository;
	
	public ValoracionServiceImpl(ValoracionRepository valoracionRepository) {
		this.valoracionRepository = valoracionRepository;
	}

	/*@Override
	public List<Valoracion> getValoracionesFromReceta(Integer id) {
		return this.valoracionRepository.findValoracionesByReceta(id);
	}*/
	
	@Override
	public Long getValoracionesFromReceta(Integer id) {
		Long salida=this.valoracionRepository.findValoracionesByReceta(id);
		if (salida!=null) return salida;
		else return (long) 0;
	}

	@Override
	public Valoracion getValoracionFromUser(Integer recetaid, String usuarioid) {
		return this.valoracionRepository.findValoracionByUser(recetaid, usuarioid).get();
	}

	@Override
	public Valoracion addValoracion(Valoracion valoracion) {
		return this.valoracionRepository.save(valoracion);
	}

	@Override
	public Valoracion editValoracion(Valoracion valoracion) {
		this.valoracionRepository.findValoracionByUser(valoracion.getReceta().getId(), valoracion.getUser().getUsername()).orElseThrow();
		
		return this.valoracionRepository.save(valoracion);
	}

	@Override
	public void deleteValoracion(Integer id) {
		this.valoracionRepository.deleteById(id);
		
	}

}
