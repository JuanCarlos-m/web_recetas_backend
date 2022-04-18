package com.recetas.service;

import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.Usuarios;

import java.util.List;

public interface UserService {

    List<Usuarios> getAllActive();
    List<Usuarios> getAll();
    //Usuarios getUser(String id);
    void switchActive(String nameUsuario) throws EntityNotFoundException;
    Usuarios findByUsername(String nameUsuario) throws EntityNotFoundException;
    Usuarios create(Usuarios usuarios);
    void delete(String nameUsuario) throws EntityNotFoundException;

    List<Usuarios> getFollows(String username, Integer pageNo, Integer pageSize, String sortBy);
    List<Usuarios>getFollowers(String username, Integer pageNo, Integer pageSize, String sortBy); 
    
    void addFollow(String idSeguidor, String idSeguido);
	boolean checkFollow(String seguidor, String seguido);
}
