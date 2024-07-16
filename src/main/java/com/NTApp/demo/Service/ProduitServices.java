package com.NTApp.demo.Service;

import com.NTApp.demo.DTO.ProduitsDto;
import com.NTApp.demo.Models.ProduitEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProduitServices {


     ResponseEntity<String> creeproduits(ProduitsDto produitsDto);

     List<ProduitEntity> getAllproduits() ;


    Optional<ProduitEntity> getproduitsByid(long id);

    ResponseEntity<String> delectproduits(long id);

    ResponseEntity<String> updateproduits(long id, ProduitsDto produitsDto);
}
