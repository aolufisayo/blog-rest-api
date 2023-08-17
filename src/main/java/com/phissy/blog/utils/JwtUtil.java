package com.phissy.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final Long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512 , secret)
                .compact();

    }

    private <T> T getClaimsFromToken(String token , Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userdetails){
        final String username = getUsernameFromToken(token);
        return username.equalsIgnoreCase(userdetails.getUsername()) && !isTokenExpired(token);
    }


}


