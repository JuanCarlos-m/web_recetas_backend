package com.recetas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recetas.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query(value = "select * from comentarios where receta_id_receta=:id", nativeQuery = true)
	Page<Comentario> findByRecetaid(@Param("id") Integer id, Pageable pageable);

}
