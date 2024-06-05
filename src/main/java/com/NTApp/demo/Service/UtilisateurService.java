package com.NTApp.demo.Service;


import com.NTApp.demo.DTO.UtilisateurDto;
import org.springframework.http.ResponseEntity;



public interface UtilisateurService {

   ResponseEntity<String> creerutilisateur( UtilisateurDto utilisateurs);
}
