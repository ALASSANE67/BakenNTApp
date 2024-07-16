package com.NTApp.demo.Controlleur;

import com.NTApp.demo.DTO.ProduitsDto;
import com.NTApp.demo.Models.ProduitEntity;
import com.NTApp.demo.Service.ProduitServices;
import com.NTApp.demo.Web.ProduitsWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProduitsController implements ProduitsWeb {

    @Autowired
    private ProduitServices produitServices;
    @Override
    public ResponseEntity<String> creerproduits(ProduitsDto produitsDto) {
        return produitServices.creeproduits(produitsDto);
    }

    @Override
    public List<ProduitEntity> Allproduits() {
        return produitServices.getAllproduits();
    }

    @Override
    public Optional<ProduitEntity> AllproduitsrByid(long id) {
        return produitServices.getproduitsByid(id);
    }

    @Override
    public ResponseEntity<String> delectproduitsrByid(long id) {
        return produitServices.delectproduits(id);
    }

    @Override
    public ResponseEntity<String> updateproduitsrByid(long id, ProduitsDto produitsDto) {
        return produitServices.updateproduits(id,produitsDto);
    }
}
