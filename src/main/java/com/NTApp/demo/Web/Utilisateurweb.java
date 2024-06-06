package com.NTApp.demo.Web;



import com.NTApp.demo.DTO.AuthentificationDto;
import com.NTApp.demo.DTO.UtilisateurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping(path = "/utilisateur")
@CrossOrigin("*")
public interface Utilisateurweb {

    @PostMapping("/incription")
    @ResponseBody
     ResponseEntity<String> creerutilisateur(@RequestBody UtilisateurDto utilisateurs);


    @PostMapping(path = "/connection")
    @ResponseBody
    Map<String,String> connection(@RequestBody AuthentificationDto authentificationDto );

}
