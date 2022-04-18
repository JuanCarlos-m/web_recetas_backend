package com.recetas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Page<Receta> findBySearch(@Param("input")String input, Pageable pageable);
	
	Page<Receta> findAllByCategoria(Categoria categoria, Pageable pageable);
	
	@Query(value = "select * from recetas where recetas.user_username=:user", nativeQuery = true)
	Page<Receta> findAllByUsername(@Param("user") String username, Pageable pageable);
}
