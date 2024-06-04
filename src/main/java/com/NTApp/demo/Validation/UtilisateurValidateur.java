package com.NTApp.demo.Validation;

import com.NTApp.demo.DTO.UtilisateurDto;

public class UtilisateurValidateur {
    public  static  boolean UtilisateurValidateur(UtilisateurDto utilisateurDto){
        if (utilisateurDto.getEmail() != null &&
            utilisateurDto.getNom() != null &&
                utilisateurDto.getPrenom() != null &&
                utilisateurDto.getTelephone() != null){
            return true;
        }

        return false;
    }
}
