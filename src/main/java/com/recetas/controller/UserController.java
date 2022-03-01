package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Receta;
import com.recetas.model.User;
import com.recetas.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
	
	@GetMapping("/{id}")
	public User getUser (@PathVariable("id") Integer id) {
		return this.userService.getUser(id);
	}
	
	@GetMapping("/recetas/{id}")
	public List<Receta> getRecetasByUser (@PathVariable("id") Integer id) {
		return this.userService.getRecetasByUser(id);
	}
}
