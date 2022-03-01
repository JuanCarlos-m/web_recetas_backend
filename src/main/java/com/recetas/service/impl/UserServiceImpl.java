package com.recetas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Receta;
import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import com.recetas.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public User getUser(Integer id) {
		
		return this.userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {

		return this.userRepository.findAll();
	}

	@Override
	public List<Receta> getRecetasByUser(Integer id) {
		return this.userRepository.findById(id).get().getRecetas();
	}
}
