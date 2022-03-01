package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recetas.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
