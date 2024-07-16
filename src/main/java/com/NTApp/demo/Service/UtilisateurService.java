package com.NTApp.demo.Service;


import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Models.Utilisateurs;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UtilisateurService {

   ResponseEntity<String> creerutilisateur( UtilisateurDto utilisateurs);

    List<Utilisateurs> getAllUtilisateur();

    Optional<Utilisateurs> getutilisateurByid(long id);

    ResponseEntity<String> updaterUtilisateur(UtilisateurDto utilisateurs, long id);

    ResponseEntity<String> deletutilisateur(long id);
}
