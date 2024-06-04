package com.NTApp.demo.Controlleur;

import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Service.UtilisateurService;
import com.NTApp.demo.Web.Utilisateurweb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class utilisateurControleur implements Utilisateurweb {
   private UtilisateurService utilisateurService;

    @Override
    public ResponseEntity<String> creerutilisateur(UtilisateurDto utilisateurs) {
        return utilisateurService.creerutilisateur(utilisateurs);
    }

}
