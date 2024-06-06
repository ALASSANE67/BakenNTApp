package com.NTApp.demo.Controlleur;

import com.NTApp.demo.DTO.AuthentificationDto;
import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Jwt.JwtService;
import com.NTApp.demo.Service.UtilisateurService;
import com.NTApp.demo.Web.Utilisateurweb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class utilisateurControleur implements Utilisateurweb {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;



    @Override
    public ResponseEntity<String> creerutilisateur(UtilisateurDto utilisateurs) {
        return utilisateurService.creerutilisateur(utilisateurs);
    }

    @Override
    public Map<String, String> connection(AuthentificationDto authentificationDto) {
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
