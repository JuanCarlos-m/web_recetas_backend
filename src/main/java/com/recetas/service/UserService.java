package com.recetas.service;

//import com.recetas.dto.UserDTO;
import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.Receta;
import com.recetas.model.Usuarios;

import java.util.List;

public interface UserService {

    List<Usuarios> getAllActive();
    List<Usuarios> getAll();
    Usuarios getUser(String id);
    void switchActive(String nameUsuario) throws EntityNotFoundException;
    Usuarios findByName(String nameUsuario) throws EntityNotFoundException;
    Usuarios create(Usuarios usuarios);
    void delete(String nameUsuario) throws EntityNotFoundException;

    List<Receta> getRecetasByUser(String id);
    List<Usuarios> getFollows(String id);
    List<Usuarios>getFollowers(String id); 
    
    void addFollow(String idSeguidor, String idSeguido);
}
