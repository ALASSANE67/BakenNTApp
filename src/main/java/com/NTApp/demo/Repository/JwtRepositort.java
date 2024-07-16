package com.NTApp.demo.Repository;

import com.NTApp.demo.Models.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface JwtRepositort extends JpaRepository<Jwt,Long> {

    Optional<Jwt>  findByValeurAndDesactiveAndExpire(@Param("valeur") String valeur,@Param("desactive") boolean desactive,@Param("expire") boolean expire);

    @Query("FROM Jwt j WHERE j.expire = :expire and j.desactive= :desactive and j.utilisateurs.email = :email")
    Optional<Jwt>  findByUtilisateurTokenValid(@Param("email") String email,@Param("desactive") boolean desactive,@Param("expire") boolean expire);

    @Query("FROM Jwt j WHERE j.utilisateurs.email = :email")
    Stream<Jwt> findByUtilisateur(@Param("email") String email);

    @Query("FROM Jwt j WHERE j.refrechToken.valeur = :valeur")
    Optional<Jwt> findByRefrechToken(@Param("valeur") String valeur);

}
