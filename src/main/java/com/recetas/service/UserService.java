package com.recetas.service;

import java.util.List;

import com.recetas.model.Receta;
import com.recetas.model.User;

public interface UserService {

	User getUser(Integer id);

	List<User> getAllUsers();

	List<Receta> getRecetasByUser(Integer id);

}
