package com.NTApp.demo.Jwt;

import com.NTApp.demo.Models.Jwt;
import com.NTApp.demo.Models.RefrechToken;
import com.NTApp.demo.Models.Utilisateurs;
import com.NTApp.demo.Repository.JwtRepositort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Component

@AllArgsConstructor
@Slf4j
public class JwtService {
    public static final String BEARER = "bearer";
    public static final String REFRECH = "refrech";
    public static final String TOKEN_IS_INVALIDE = "Token is invalide";
    private final  String ENCRIPTION_KEY = "b0fdadb687f199a569367f0a6bcfdb0fdadb687f199a569367f0a6bcfd";

    private final UserDetailsService userDetailsService;
    private final JwtRepositort jwtRepositort;



    public Jwt tokenByValeur(String valeur) {
        return  this.jwtRepositort
                .findByValeurAndDesactiveAndExpire(
                        valeur
                        ,false,
                        false)
                .orElseThrow(()-> new RuntimeException("Token inconn!"));
    }

    public Map<String,String> genererToken(String email){
        Utilisateurs utilisateur = (Utilisateurs) this.userDetailsService.loadUserByUsername(email);
        Map<String, String> jwtMap = new HashMap<>(this.genereToken(utilisateur));

        RefrechToken refrechToken = RefrechToken.builder()
                                .creation(Instant.now())
                                .valeur(UUID.randomUUID().toString())
                                .expiration(Instant.now().plusMillis(15*30*1000))
                                .expire(false)
                               .build();

        Jwt jwt = Jwt.builder()
                .valeur(jwtMap.get(BEARER))
                .utilisateurs(utilisateur)
                .expire(false)
                .desactive(false)
                .refrechToken(refrechToken)
                .build();
        jwtRepositort.save(jwt);

        jwtMap.put("resulta","pass");
        jwtMap.put(REFRECH,refrechToken.getValeur());
        return jwtMap;
    }
    public  Map<String, String> genereToken(Utilisateurs utilisateurs){
      final long instant = System.currentTimeMillis();
     final long expiration = instant+30*60*10000;

     final   Map<String, Object> claim = Map.of(
                "nom",utilisateurs.getNom(),
              "email",utilisateurs.getEmail()
        );

        String bearer =
                Jwts.builder()
                        .issuedAt(new Date(instant))
                        .expiration(new Date(expiration))
                        .subject(utilisateurs.getEmail())
                        .claims(claim)
                        .signWith(getKey(), SignatureAlgorithm.HS256)
                        .compact();

        return Map.of(BEARER,bearer);
    }
    private Key getKey(){
        final byte[] decode = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(decode) ;
    }
    public String extraUsername(String token) {
        return this.getClaim(token,Claims::getSubject);
    }

    public boolean istokenExpire(String token) {
        Date expiration = this.getClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private <T>T getClaim(String token, Function <Claims, T> function) {
        Claims claims = getallClaimes(token);
        return function.apply(claims);
    }

    private Claims getallClaimes(String token) {
        return Jwts
                .parser()
                .setSigningKey(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

    public boolean ValidationToken(String token, UserDetails userDetails) {
        final  String utilisateur =extraUsername(token);
        return (utilisateur.equals(userDetails.getUsername()) && !istokenExpire(token));
    }

    public void deconnection() {
        Utilisateurs utilisateur = (Utilisateurs) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt byUtilisateurTokenValid = this
                .jwtRepositort
                .findByUtilisateurTokenValid(utilisateur.getEmail(), false, false)
                .orElseThrow(()-> new RuntimeException("Token invalide"));
        byUtilisateurTokenValid.setExpire(true);
        byUtilisateurTokenValid.setDesactive(true);
        this.jwtRepositort.save(byUtilisateurTokenValid);
    }

    public void desactiveToken(Utilisateurs utilisateur){
        List<Jwt> jWtList = jwtRepositort.findByUtilisateur(utilisateur.getEmail()).map(
                jWt -> {
                    jWt.setDesactive(true);
                    jWt.setExpire(true);
                    return jWt;
                }
        ).collect(Collectors.toList());
        this.jwtRepositort.saveAll(jWtList);
    }


    public  Map<String, String>  refreshtoken(Map<String, String> refreshToken) {
        final   Jwt tokenInvalide = this.jwtRepositort.findByRefrechToken(refreshToken.get(REFRECH)).orElseThrow(() -> new RuntimeException(TOKEN_IS_INVALIDE));
        if (tokenInvalide.getRefrechToken().isExpire() || tokenInvalide.getRefrechToken().getExpiration().isBefore(Instant.now())){
            throw new RuntimeException(TOKEN_IS_INVALIDE);
        }else {
              this.desactiveToken(tokenInvalide.getUtilisateurs());
            return this.genererToken(tokenInvalide.getUtilisateurs().getEmail());
        }
    }
}


