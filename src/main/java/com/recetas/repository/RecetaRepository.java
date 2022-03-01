package com.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recetas.model.Categoria;
import com.recetas.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
	
	@Query(value = "select * from recetas where recetas.titulo like %:input%",
			nativeQuery = true)
	List<Receta> findBySearch(@Param("input")String input);
	
	List<Receta> findAllByCategoria(Categoria categoria);
}
