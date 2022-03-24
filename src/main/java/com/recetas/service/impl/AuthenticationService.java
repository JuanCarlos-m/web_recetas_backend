package com.recetas.service.impl;

import com.recetas.exception.AccessDeniedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Service
public class AuthenticationService {

    //Token Expiration time (ms) 60 * 60 * 1000 = 3600000 1 hora
    private static final long EXPIRATIONTIME = 3600000L;
    private static final String PREFIX = "Bearer";

    
    @Value("${spring.app.jwtSecret}")
    private String SIGNINGKEY;


    public void addToken(HttpServletResponse response, String userName) {
        String jwt = Jwts.builder()
                        .setSubject(userName)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                        .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                        .compact();
        response.addHeader("Authorization", PREFIX + " " + jwt);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");
    }


    public Authentication getAuthentication (HttpServletRequest request) throws AccessDeniedException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(SIGNINGKEY)
                        .parseClaimsJws(token.replace(PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
                }
            } catch (ExpiredJwtException expired) {
                throw new AccessDeniedException("Token expirado, por favor realice login nuevamente");
            } catch (SignatureException signature) {
                throw new AccessDeniedException("Token no válido");
            }
        }
        return null;
    }
}
