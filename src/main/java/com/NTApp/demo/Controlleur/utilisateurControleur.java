package com.NTApp.demo.Controlleur;

import com.NTApp.demo.DTO.AuthentificationDto;
import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Jwt.JwtService;
import com.NTApp.demo.Service.UtilisateurService;
import com.NTApp.demo.Web.Utilisateurweb;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class utilisateurControleur implements Utilisateurweb {

   private UtilisateurService utilisateurService;
   private AuthenticationManager authenticationManager;
   private JwtService jwtService;

    public utilisateurControleur(UtilisateurService utilisateurService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<String> creerutilisateur(UtilisateurDto utilisateurs) {
        return utilisateurService.creerutilisateur(utilisateurs);
    }

    @Override
    public Map<String, Object> connection(AuthentificationDto authentificationDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentificationDto.email(),authentificationDto.motdepasse()
                )
        );
        if (authentication.isAuthenticated()){
            return this.jwtService.genererToken(authentificationDto.email());
        }
        return null;
    }

}
