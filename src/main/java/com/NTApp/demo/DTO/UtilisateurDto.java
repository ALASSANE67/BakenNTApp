package com.NTApp.demo.DTO;

import com.NTApp.demo.Models.roles;
import lombok.*;

@Getter
@Setter

public class UtilisateurDto {
private String nom;
private String prenom;
private String email;
private String telephone;
private String motdepasse;
private roles roles;

    public UtilisateurDto() {
    }

    public UtilisateurDto(String nom, String prenom, String email, String telephone, String motdepasse, com.NTApp.demo.Models.roles roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motdepasse = motdepasse;
        this.roles = roles;
    }
}
