package com.recetas.controller;



import com.recetas.dao.RoleRepository;
import com.recetas.dao.UserRepository;
import com.recetas.dto.MessageResponse;
import com.recetas.dto.UserPostDTO;
import com.recetas.model.ERole;
import com.recetas.model.Receta;
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
                encoder.encode(signUpRequest.getPassword()));

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
        this.userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    
    @GetMapping("/users")
	public List<Usuarios> getAllUsers(){
		return this.userService.getAll();
	}
	
	
	@GetMapping("/users/{id}")
	public Usuarios getUser (@PathVariable("id") String id) {
		return this.userService.getUser(id);
	}
	
	@GetMapping("/users/{id}/recetas/")
	public List<Receta> getRecetasByUser (@PathVariable("id") String id) {
		return this.userService.getRecetasByUser(id);
	}
	
	@GetMapping("/users/{id}/follow")
	public List<Usuarios> getFollows (@PathVariable("id") String id){
		return this.userService.getFollows(id);
	}
	
	@GetMapping("/users/{id}/followers")
	public List<Usuarios> getFollowers (@PathVariable("id") String id){
		return this.userService.getFollowers(id);
	}
	
	@PostMapping("/users/{id}/follow")
	public ResponseEntity<?> followUser(@PathVariable("id") String seguidorId, @RequestParam("follow") String seguidoId){
		this.userService.addFollow(seguidorId, seguidoId);
		
		return ResponseEntity.ok(null);
		
	}
}
