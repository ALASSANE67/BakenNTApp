package com.NTApp.demo.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "jwt_tb")
public class Jwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column
    private String valeur;
    @Column
    private boolean expire;

    @Column
    private boolean desactive;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "utilisateur")
    private Utilisateurs utilisateurs ;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private RefrechToken refrechToken ;
}
