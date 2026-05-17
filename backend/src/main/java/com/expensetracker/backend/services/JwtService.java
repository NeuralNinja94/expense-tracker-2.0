package com.expensetracker.backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(String benutzername, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(benutzername)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis()
                        +expirationTime))
                .signWith(getSigningKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractBenutzername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token){
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    //Methode um Generische Claim-Extraktion
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //Alle claims extrahieren aus einem Token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Token expiration prüfen
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //Token vailidieren
    public boolean validateToken(String token, String benutzername){
        final String extractedBenutzername = extractBenutzername(token);
        return (extractedBenutzername.equals(benutzername) && !isTokenExpired(token));
    }
    //Signing Key erstellen
    private Key getSigningKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
