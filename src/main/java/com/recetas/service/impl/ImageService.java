package com.recetas.service.impl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recetas.dao.RecetaRepository;
import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.Receta;

@Service
public class ImageService {

	//private RecetaRepository recetaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*public ImageService (RecetaRepository recetaRepository) {
		this.recetaRepository=recetaRepository;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean imageStore(MultipartFile file, Class entity, int id) throws IOException, EntityNotFoundException{
		try {
			Object myObject=entityManager.find(Receta.class, id);
			
			String myFileName=entity.getSimpleName()+"_"+Integer.toString(id)+"_"+file.getOriginalFilename();
			
			Path targetPath = Paths.get("./images/"+myFileName).normalize();
			
			Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			
			Method method=entity.getMethod("setImageUrl", String.class);
			method.invoke(myObject, "/download/"+myFileName);
			entityManager.persist(entity.cast(myObject));
			
			//En algun sitio tengo que guardar el nombre de la imagen, digo yo
			/*Receta receta=(Receta) myObject;
			receta.setImg(myFileName);
			this.recetaRepository.save(receta);*/
		} catch (Exception e) {
			throw new EntityNotFoundException(entity.getSimpleName()+":"+Integer.toString(id));
		}
		
		return true;
		
	}
}
