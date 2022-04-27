package com.recetas.service.impl;


import com.recetas.dao.UserRepository;
import com.recetas.dto.PagedResponse;
import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.Comentario;
import com.recetas.model.Usuarios;
import com.recetas.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    
    /*@Override
	public Usuarios getUser(String id) {
		
		return this.userRepository.findById(id).get();
	}Se buscara a los usuarios por nombre en vez de por id, esto ya no es necesario*/

    @Override
    public void switchActive(String nameUsuario) throws EntityNotFoundException {
        if (userRepository.switchUsuario(nameUsuario)!=1) {
            throw new EntityNotFoundException(String.format(USER_NOT_EXIST, nameUsuario));
        }
    }

    @Override
    public Usuarios findByUsername(String nameUsuario) throws EntityNotFoundException {

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
        Usuarios usuario = findByUsername(nameUsuario);
        if (usuario != null)
            userRepository.delete(usuario);
        else
            throw new EntityNotFoundException(String.format(USER_NOT_EXIST, nameUsuario));
    }
    
	@Override
	public PagedResponse getFollows(String id, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Usuarios> pagedResult=this.userRepository.findFollows(id, paging);
		
		if (pagedResult.hasContent()) {
			Usuarios[] usuarios=pagedResult.getContent().toArray(Usuarios[]::new);
			return new PagedResponse(usuarios, pagedResult.getTotalElements(), pagedResult.getNumberOfElements(), pagedResult.getSize());

		}else {
			return new PagedResponse();
		}
	}
	
	@Override
	public PagedResponse getFollowers(String id, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Usuarios> pagedResult=this.userRepository.findFollowers(id, paging);

		if (pagedResult.hasContent()) {
			Usuarios[] usuarios=pagedResult.getContent().toArray(Usuarios[]::new);
			return new PagedResponse(usuarios, pagedResult.getTotalElements(), pagedResult.getNumberOfElements(), pagedResult.getSize());

		}else {
			return new PagedResponse();
		}
	}
	
	@Override
	public void addFollow(String idSeguidor, String idSeguido) {
		Usuarios seguidor=this.userRepository.findById(idSeguidor).get();
		Usuarios seguido=this.userRepository.findById(idSeguido).get();
		
		seguidor.getFollows().add(seguido);
		this.userRepository.save(seguidor);
	}

	@Override
	public boolean checkFollow(String seguidor, String seguido) {
		Usuarios userSeguidor= this.userRepository.findByUsername(seguidor).get();
		Usuarios userSeguido= this.userRepository.findByUsername(seguido).get();
		
		if (userSeguidor.getFollows().contains(userSeguido)) return true;
		else return false;
		
	}

	@Override
	public void unfollow(String seguidor, String seguido) {
		Usuarios userSeguidor= this.userRepository.findByUsername(seguidor).get();
		Usuarios userSeguido= this.userRepository.findByUsername(seguido).get();
		List<Usuarios> follows=userSeguidor.getFollows();
		follows.remove(userSeguido);
		
		userSeguidor.setFollows(follows);
		
		this.userRepository.save(userSeguidor);
	}


}
