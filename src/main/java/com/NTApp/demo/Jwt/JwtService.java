package com.NTApp.demo.Jwt;

import com.NTApp.demo.Models.Utilisateurs;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Transactional
@Component
@AllArgsConstructor
public class JwtService {
    private UserDetailsService userDetailsService;

    private final  String ENCRIPTION_KEY = "b0fdadb687f199a569367f0a6bcfd";

    public Map<String,Object> genererToken(String email){
        Utilisateurs utilisateurs = (Utilisateurs) this.userDetailsService.loadUserByUsername(email);
        Map<String, Object> jwtMap = new HashMap<>(this.genererToken(utilisateurs));
        return jwtMap;
    }
    public  Map<String, String> genererToken(Utilisateurs utilisateurs){

      final long instant = System.currentTimeMillis();
     final long expiration = instant* 30 * 60 * 1000;

     final    Map<String, Object> claim = Map.of(
                "nom",utilisateurs.getNom(),
               "email",utilisateurs.getEmail(),
                "roles",utilisateurs.getRoles()
        );

     String bearer = Jwts.builder()
             .setIssuedAt(new Date(instant))
             .setExpiration(new Date(expiration))
             .setSubject(utilisateurs.getEmail())
             .setClaims(claim)
             .signWith(getKey(), SignatureAlgorithm.HS256)
              .compact();

        return Map.of("bearer",bearer);
    }
    private Key getKey(){
      final  byte[] bytes = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
    public String extraUsername(String token) {
        return this.getClaim(token,Claims::getSubject);
    }

    public boolean istokenExpire(String token) {
        Date isexspiration = this.getClaim(token, Claims::getExpiration);
        return isexspiration.before(new Date());
    }

    private <T>T getClaim(String token, Function <Claims, T> function) {
        Claims claims= getallClaimes(token);
        return function.apply(claims);
    }

    private Claims getallClaimes(String token) {
        return  Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean ValidationToken(String token, UserDetails userDetails) {
        final  String utilisateur =extraUsername(token);
        return utilisateur.equals(userDetails.getUsername());
    }
}
