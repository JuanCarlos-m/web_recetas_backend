package com.recetas.dao;

import com.recetas.model.ERole;
import com.recetas.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {

    Optional<Roles> findByName(ERole role);

}
