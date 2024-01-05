package com.meetandcraft.api.security.JWT;

import com.meetandcraft.api.security.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.Jwts;

@Component
public class JWTGenerator {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken (Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date ();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
        return token;
    }

    public String getUsernameFromJWT (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception exception){
            throw  new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
