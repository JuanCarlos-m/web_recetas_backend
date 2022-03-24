package com.recetas.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recetas.model.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
	
	@Query(value = "select * from valoraciones where receta_id=:id",
			nativeQuery = true)
	List<Valoracion> findValoracionesByReceta (@Param("id") Integer id);
	
	@Query(value = "select * from valoraciones where receta_id=:recetaid and user_id=:userid",
			nativeQuery = true)
	Optional<Valoracion> findValoracionByUser(@Param("recetaid") Integer recetaid, @Param("userid") String userid);

}
