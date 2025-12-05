package com.mystore.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class JwtUtils {
	private static final Logger logger = LogManager.getLogger(JwtUtils.class);

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.time.expiration}")
	private String timeExpiration;

	/**
	 * Generate access token
	 * 
	 * @param username
	 * @return
	 */
	public String generateAccesToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact();
	}

	/**
	 * Validate the access token
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(getSignatureKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
			return true;
			
		}catch (Exception e) {
			
			logger.error("token invalido, error: ".concat(e.getMessage()));
			return false;
		}
	}
	
	/**
	 * Get the token username
	 * 
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	/**
	 * Obtain a single claim.
	 * 
	 * @param <T>
	 * @param token
	 * @param claimsTFunction
	 * @return
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = extracAllClaims(token);
		return claimsTFunction.apply(claims);
	}

	/**
	 * Get all claims.
	 * 
	 * @param token
	 * @return
	 */
	public Claims extracAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token).getBody();
	}

	/**
	 * Obtain token signature 
	 * 
	 * @return key
	 * @throws DecoderException
	 */
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
