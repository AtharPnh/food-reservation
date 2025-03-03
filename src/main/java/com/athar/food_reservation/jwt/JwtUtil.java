package com.athar.food_reservation.jwt;

import com.athar.food_reservation.entities.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.swing.text.StyledEditorKit;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "ceb3b42812447044d8dbc41408e68922a0a828ee3a15e97af8eb81f1d422dce9bf1cc849d1c52d848" +
            "5aa7bce08a4b2c177d4fcf5d389c29078c1703f46a312f985aafd0420375995cf29a8403767613c3cf0e79f5cbef7eae1859452a6cb" +
            "822057c9a2c9e380efa26ed47b027d2948723dde0a6a49e2161d9e470dcd79048fdf66f14bda3b3c7525bf6ad68d46861efc7dd43aa" +
            "3e59ff73a284ddd3109aeb0535de8a0c64ee43e4afa02057f1468f8ebfb1748778544ebe5b5e3e30be87960523c21c7ed14da8ca568" +
            "48fa242d9c3476dce2701cc4f8d1532ce33adcdd893b9561556e47b9168ab5e870cdcdac02df5ef4e61aec94d46e26a3ef04f67729e0fe";

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return createToken(extraClaims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet()));
        return  generateToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }
}
