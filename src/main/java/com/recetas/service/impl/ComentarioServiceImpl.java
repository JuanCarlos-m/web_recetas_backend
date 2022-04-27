package com.recetas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.recetas.model.Comentario;
import com.recetas.model.Receta;
import com.recetas.dao.ComentarioRepository;
import com.recetas.dto.PagedResponse;
import com.recetas.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	private final ComentarioRepository comentarioRepository;
	
	public ComentarioServiceImpl (ComentarioRepository comentarioRepository) {
		this.comentarioRepository=comentarioRepository;
	}
	
	@Override
	public PagedResponse getComentariosFromReceta(Integer id, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Comentario> pagedResult=this.comentarioRepository.findByRecetaid(id, paging);
		
		if (pagedResult.hasContent()) {
			Comentario[] comentarios=pagedResult.getContent().toArray(Comentario[]::new);
			return new PagedResponse(comentarios, pagedResult.getTotalElements(), pagedResult.getNumberOfElements(), pagedResult.getSize());

		}else {
			return new PagedResponse();
		}
		//return this.comentarioRepository.findByRecetaid(id);
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
