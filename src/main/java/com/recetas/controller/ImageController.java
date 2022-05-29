package com.recetas.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recetas.model.Receta;
import com.recetas.service.impl.ImageService;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@PostMapping(value = "/upload")
	public ResponseEntity<Object> imageUpload(@RequestParam("id") String id, @RequestParam("entity") String entity, @RequestParam("file") MultipartFile file){
		try {
			//String packageName=this.getClass().getPackageName();
			//No se como va esto pero lo ponga como lo ponga no tira.
			//Class<?> entityClass=Class.forName("com.recetas.model.Receta");
			//Class<?> entityClass=Class.forName(packageName.substring(0,packageName.lastIndexOf("."))+".model"+entity);
			imageService.imageStore(file, Receta.class, Integer.parseInt(id));
		}catch (IOException e) {
			return new ResponseEntity<>("Error en el archivo", HttpStatus.NO_CONTENT);
			
		}/*catch (Exception e) {
			throw new EntityNotFoundException(entity);
		}*/ 
			catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (com.recetas.exception.EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/download/{name}")
	public ResponseEntity<Resource> getImage(@PathVariable("name") String name){
		Path targetPath=Paths.get("./images/"+name).normalize();
		
		try {
			Resource resource = new UrlResource(targetPath.toUri());
			if(resource.exists()) {
				String contentType=Files.probeContentType(targetPath);
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
			}
		} catch (IOException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
