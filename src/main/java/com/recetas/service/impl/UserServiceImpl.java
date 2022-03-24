package com.recetas.service.impl;


import com.recetas.dao.UserRepository;
//import com.recetas.dto.UserDTO;
import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.Receta;
import com.recetas.model.Usuarios;
import com.recetas.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final String USER_NOT_EXIST = "Usuario con nombre '%s' no existe";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Usuarios> getAllActive() {
        return userRepository.findAllByActiveTrue();
    }

    @Override
    public List<Usuarios> getAll() {
        return userRepository.findAll();
    }
    
    @Override
	public Usuarios getUser(String id) {
		
		return this.userRepository.findById(id).get();
	}

    @Override
    public void switchActive(String nameUsuario) throws EntityNotFoundException {
        if (userRepository.switchUsuario(nameUsuario)!=1) {
            throw new EntityNotFoundException(String.format(USER_NOT_EXIST, nameUsuario));
        }
    }

    @Override
    public Usuarios findByName(String nameUsuario) throws EntityNotFoundException {

        return userRepository.findByUsername(nameUsuario).orElseThrow(
                ()-> new EntityNotFoundException(String.format(USER_NOT_EXIST, nameUsuario))
        );
    }

    @Override
    public Usuarios create(Usuarios usuario) {

        return userRepository.save(usuario);

    }

    @Override
    public void delete(String nameUsuario) throws EntityNotFoundException {
        Usuarios usuario = findByName(nameUsuario);
        if (usuario != null)
            userRepository.delete(usuario);
        else
            throw new EntityNotFoundException(String.format(USER_NOT_EXIST, nameUsuario));
    }
    

	@Override
	public List<Receta> getRecetasByUser(String id) {
		return this.userRepository.findById(id).get().getRecetas();
	}
}
