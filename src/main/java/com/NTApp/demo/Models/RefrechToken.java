package com.NTApp.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "refrechToken_tb")
public class RefrechToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column
    private String valeur;
    @Column
    private boolean expire;
    @Column
    private Instant expiration;
    @Column
    private Instant creation ;
}
