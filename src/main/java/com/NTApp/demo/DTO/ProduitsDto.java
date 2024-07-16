package com.NTApp.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitsDto {

    private long id ;
    private String nom;
    private long quantite;
    private long prix;
    private String marque;
    public ProduitsDto() {
    }

    public ProduitsDto(long id, String nom, long quantite, long prix, String marque) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.marque = marque;
    }
}
