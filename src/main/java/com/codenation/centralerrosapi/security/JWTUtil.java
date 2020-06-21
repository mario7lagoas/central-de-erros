package com.codenation.centralerrosapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) { // Gera o Token
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject(); //Pega o usuário
			Date expirationDate = claims.getExpiration(); //Data de Expiração
			Date now = new Date(System.currentTimeMillis()); //Data Atual
			if (username != null && expirationDate != null && now.before(expirationDate)) { //Validando o token
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) { // Claims = Reivindicações do token
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody(); //Recupera os claims de agordo com o token
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) { // Retorna o usuario
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
}



