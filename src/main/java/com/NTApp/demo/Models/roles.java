package com.NTApp.demo.Models;


import com.NTApp.demo.TypeLibell;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "roles_tb")
public class roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "libelle")
    @Enumerated(EnumType.STRING)
    private TypeLibell libelle;

}
