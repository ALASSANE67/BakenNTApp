package com.NTApp.demo.DTO;

import com.NTApp.demo.Models.roles;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

private String nom;
private String prenom;
private String email;
private String telephone;
    private String motdepasse;
private roles roles;

}
