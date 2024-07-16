package com.NTApp.demo.Repository;

import com.NTApp.demo.Models.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitsRepository extends JpaRepository<ProduitEntity,Long> {
}
