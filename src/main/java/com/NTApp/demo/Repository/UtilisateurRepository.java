package com.NTApp.demo.Repository;

import com.NTApp.demo.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long> {
    Optional<Utilisateurs> findByEmail(@Param("email") String email);
}
