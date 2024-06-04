package com.NTApp.demo.Web;



import com.NTApp.demo.DTO.UtilisateurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/utilisateur")
@CrossOrigin("*")
public interface Utilisateurweb {
    @PostMapping("/incription")
     ResponseEntity<String> creerutilisateur(@RequestBody UtilisateurDto utilisateurs);
}
