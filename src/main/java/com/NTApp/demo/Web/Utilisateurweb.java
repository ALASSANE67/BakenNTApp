package com.NTApp.demo.Web;



import com.NTApp.demo.DTO.AuthentificationDto;
import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Models.Utilisateurs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequestMapping(path = "/utilisateur")
@CrossOrigin("*")
public interface Utilisateurweb {

    @PostMapping("/incription")
    @ResponseBody
     ResponseEntity<String> creerutilisateur(@RequestBody UtilisateurDto utilisateurs);


    @PostMapping(path = "/connection")
    @ResponseBody
    Map<String,String> connection(@RequestBody AuthentificationDto authentificationDto );

    @PostMapping(path = "/refrech_token")
    @ResponseBody
     Map<String, String> RefreshToken(Map<String,String> refreshToken) ;


    @GetMapping(path = "/tout")
    @ResponseBody
     List<Utilisateurs> Allutilisateur();

    @GetMapping(path = "/{id}")
    @ResponseBody
    Optional<Utilisateurs> AllutilisateurByid(@PathVariable long id);


    @DeleteMapping(path = "/delect/{id}")
    @ResponseBody
    ResponseEntity<String> deletutilisateurByid(@PathVariable long id);

    @PatchMapping (path = "/update/{id}")
    @ResponseBody
    ResponseEntity<String> updateutilisateurByid(@RequestBody UtilisateurDto utilisateurs ,@PathVariable long id);

}
