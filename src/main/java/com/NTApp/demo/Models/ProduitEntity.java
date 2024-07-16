package com.NTApp.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "produits_tb")
public class ProduitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column

    @Size(max = 300)
    private String nom;
    @Column

    @Size(max = 300)
    private long quantite;
    @Column
    @Size(max = 300)
    @Email
    private long prix;
    @Column
    @Size(max = 300)
    private String marque;
}
