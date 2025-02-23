package com.tel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tel.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long>{
    
    List<Catalog> findByTipo(Catalog.TipoConcesionario tipo); // Usa el enum, no String
    
}
