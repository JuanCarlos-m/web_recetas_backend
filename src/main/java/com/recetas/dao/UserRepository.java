package com.recetas.dao;

import com.recetas.model.Usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, String> {

    @Override
    Optional<Usuarios> findById(String s);

    Optional<Usuarios> findByName(String userName);

    Boolean existsByName(String userName);


    List<Usuarios> findAllByActiveTrue();

    @Modifying
    @Query(value = "update Usuarios c " +
            "set c.active = case c.active when true then false when false then true else c.active end " +
            "where c.name = :nombreUsuario")
    Integer switchUsuario(@Param("nombreUsuario") String nombreUsuario);

    Optional<Usuarios> findByUsername(String username);
    
    @Query(value = "select usuarios.* "
    		+ "from usuarios, follows "
    		+ "where follows.seguido=:id and usuarios.username=follows.seguidor", nativeQuery = true)
    Page<Usuarios> findFollowers(@Param("id") String id, Pageable pageable);
    
    @Query(value = "select usuarios.* "
    		+ "from usuarios, follows "
    		+ "where follows.seguido=usuarios.username and :id=follows.seguidor", nativeQuery = true)
    Page<Usuarios> findFollows(@Param("id") String id, Pageable pageable);
}
