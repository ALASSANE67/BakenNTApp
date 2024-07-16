package com.NTApp.demo.Validation;

import com.NTApp.demo.DTO.ProduitsDto;


public class ProduitsValidation {
    public  static  boolean ProduitsValidation(ProduitsDto produitsDto){
        if (produitsDto.getNom() != null &&
                produitsDto.getMarque() != null ){
            return true;
        }

        return false;
    }
}
