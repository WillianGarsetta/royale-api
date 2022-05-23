package br.com.royale.crm.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {
	
	@Value("${api.jwt.secret}") 
	private String secret;

	public Boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return Boolean.TRUE;
		} catch (Exception e) {
			
			return Boolean.FALSE;
		}
		
	}

	public String getIdUser(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return body.getSubject();
	}

}
