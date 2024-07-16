package com.NTApp.demo.Web;

import com.NTApp.demo.DTO.ProduitsDto;
import com.NTApp.demo.Models.ProduitEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/produits")
@CrossOrigin("*")
public interface ProduitsWeb {

    @PostMapping("/cree")
    @ResponseBody
    ResponseEntity<String> creerproduits(@RequestBody ProduitsDto produitsDto);

    @GetMapping(path = "/tout")
    @ResponseBody
    List<ProduitEntity> Allproduits();

    @GetMapping(path = "/{id}")
    @ResponseBody
    Optional<ProduitEntity> AllproduitsrByid(@PathVariable long id);

    @DeleteMapping(path = "/delect/{id}")
    @ResponseBody
    ResponseEntity<String> delectproduitsrByid(@PathVariable long id);

    @PatchMapping(path = "/update/{id}")
    @ResponseBody
    ResponseEntity<String>updateproduitsrByid(@PathVariable long id,@RequestBody ProduitsDto produitsDto);

}
