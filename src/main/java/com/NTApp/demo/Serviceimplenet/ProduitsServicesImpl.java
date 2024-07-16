package com.NTApp.demo.Serviceimplenet;

import com.NTApp.demo.DTO.ProduitsDto;
import com.NTApp.demo.Environement.Environnement;
import com.NTApp.demo.Models.ProduitEntity;
import com.NTApp.demo.Repository.ProduitsRepository;
import com.NTApp.demo.Service.ProduitServices;
import com.NTApp.demo.Utils.UtilisClasse;
import com.NTApp.demo.Validation.ProduitsValidation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ProduitsServicesImpl implements ProduitServices {
    @Autowired
    private ProduitsRepository produitsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> creeproduits(ProduitsDto produitsDto) {
        if(ProduitsValidation.ProduitsValidation(produitsDto));{
            ProduitEntity produitEntity=modelMapper.map(produitsDto,ProduitEntity.class);
            produitsRepository.save(produitEntity);
            return UtilisClasse.getResponseEntity(Environnement.PRODUITS_CREE, HttpStatus.CREATED);
        }
     //   return UtilisClasse.getResponseEntity(Environnement.PRODUITS_NON_CREE, HttpStatus.NOT_ACCEPTABLE);


    }

    @Override
    public List<ProduitEntity> getAllproduits() {
        return new ArrayList<>(produitsRepository.findAll());
    }

    @Override
    public Optional<ProduitEntity> getproduitsByid(long id) {
        Optional<ProduitEntity> produitEntity = produitsRepository.findById(id);
        return produitEntity;
    }

    @Override
    public ResponseEntity<String> delectproduits(long id) {
        Optional<ProduitEntity> produitEntity = produitsRepository.findById(id);
              if (produitEntity.isPresent()){
              ProduitEntity produitEntity1 = modelMapper.map(produitEntity,ProduitEntity.class);
                  produitsRepository.delete(produitEntity1);
                  return UtilisClasse.getResponseEntity(Environnement.PRODUITS_DELECT, HttpStatus.CREATED);
              }
        return UtilisClasse.getResponseEntity(Environnement.PRODUITS_NON_DELECT, HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<String> updateproduits(long id, ProduitsDto produitsDto) {
        Optional<ProduitEntity> produitEntity = produitsRepository.findById(id);
           if(produitEntity.isPresent()){
            ProduitEntity produitEntity1 = produitEntity.get();
            produitEntity1.setNom(produitsDto.getNom());
            produitEntity1.setQuantite(produitsDto.getQuantite());
            produitEntity1.setPrix(produitEntity1.getPrix());
            produitEntity1.setMarque(produitsDto.getMarque());
            produitsRepository.save(produitEntity1);
            return UtilisClasse.getResponseEntity(Environnement.PRODUITS_UPDATE, HttpStatus.CREATED);
        }

        return UtilisClasse.getResponseEntity(Environnement.PRODUITS_NON_UPDATE, HttpStatus.CREATED);

    }
}
