package com.topyougo.productimport.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.topyougo.productimport.service.impl.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${grokonez.app.jwtSecret}")
	private String jwtSecret;

	@Value("${grokonez.app.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public boolean validateJwtToken(String authToken) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken) != null ? true : false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String invalidateToken(String authToken) {
		return Jwts.parser()
			.setSigningKey(jwtSecret)
		   .parseClaimsJws(authToken)
		   .getBody().getSubject();
	}
}