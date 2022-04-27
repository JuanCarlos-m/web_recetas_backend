package com.recetas.controller;



import com.recetas.dao.RoleRepository;
import com.recetas.dao.UserRepository;
import com.recetas.dto.MessageResponse;
import com.recetas.dto.PagedResponse;
import com.recetas.dto.UserPostDTO;
import com.recetas.exception.EntityNotFoundException;
import com.recetas.model.ERole;
import com.recetas.model.Roles;
import com.recetas.model.Usuarios;
import com.recetas.service.UserService;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;
    private static String ERRORTOKENNOTFOUND = "Error: Username is already taken!";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    
    private final UserService userService;
    
    public UserController (UserService userService) {
    	this.userService=userService;
    }


    @GetMapping("/isloged")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> authenticateUser(@RequestHeader("Authorization") String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.replace("Bearer ",""));
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException | SignatureException | MalformedJwtException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> registerUser(@RequestBody UserPostDTO signUpRequest) {

        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Usuarios user = new Usuarios(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getLastname(),
                signUpRequest.getFecha_nac());

        List<String> strRoles = signUpRequest.getRoles();
        List<Roles> roles = new ArrayList<>();

        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ERRORTOKENNOTFOUND));
            roles.add(userRole);
        } else {
            for (String role: strRoles) {

                switch (role.toUpperCase()) {
                    case "ADMIN":
                        Roles adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ERRORTOKENNOTFOUND));
                        roles.add(adminRole);

                        break;
                    default:
                        throw new RuntimeException(ERRORTOKENNOTFOUND);
                }
            }
        }

        user.setRoles(roles);
        user.setCreatedAt(new Date());
        this.userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    
    @GetMapping("/users")
	public List<Usuarios> getAllUsers(){
		return this.userService.getAll();
	}
	
	
	@GetMapping("/users/{username}")
	public Usuarios getUser (@PathVariable("username") String username) throws EntityNotFoundException {
		return this.userService.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/follow")
	public PagedResponse getFollows (@PathVariable("username") String username, @RequestParam(name = "no", defaultValue = "1") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "username") String sortBy){
		return this.userService.getFollows(username, pageNo-1, pageSize, sortBy);
	}
	
	@GetMapping("/users/{username}/followers")
	public PagedResponse getFollowers (@PathVariable("username") String username, @RequestParam(name = "no", defaultValue = "1") Integer pageNo, @RequestParam(name = "size",defaultValue = "10") Integer pageSize,@RequestParam(name = "sort",defaultValue = "username") String sortBy){
		//Añadir el parametro Size aqui no funciona por algun motivo, asi que no se añade.
		return this.userService.getFollowers(username, pageNo-1, pageSize, sortBy);
	}
	
	@PostMapping("/users/{username}/follow")
	public ResponseEntity<?> followUser(@PathVariable("username") String seguidorId, @RequestParam("follow") String seguidoId){
		this.userService.addFollow(seguidorId, seguidoId);
		
		return ResponseEntity.ok(null);
		
	}
	
	@PutMapping("/users/{username}/unfollow")
	public ResponseEntity<?> unfollowUser(@PathVariable("username") String seguidorId, @RequestParam("follow") String seguidoId){
		this.userService.unfollow(seguidorId, seguidoId);
		
		return ResponseEntity.ok(null);
		
	}
	
	@GetMapping("/users/{username}/follow/{userfollowed}")
	public ResponseEntity<Boolean> ifFollowed(@PathVariable("username") String seguidor, @PathVariable("userfollowed") String seguido){
		if (this.userService.checkFollow(seguidor,seguido)) return ResponseEntity.ok(true);
		else return ResponseEntity.ok(false);
	}
}
