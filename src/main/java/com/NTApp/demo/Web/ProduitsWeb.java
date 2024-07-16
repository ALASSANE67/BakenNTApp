package com.NTApp.demo.Web;

import com.NTApp.demo.DTO.AuthentificationDto;
import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Models.Utilisateurs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(path = "/produits")
@CrossOrigin("*")
public interface produitsWeb {

    @PostMapping("/incription")
    @ResponseBody
    ResponseEntity<String> creerproduits(@RequestBody UtilisateurDto utilisateurs);


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

}
