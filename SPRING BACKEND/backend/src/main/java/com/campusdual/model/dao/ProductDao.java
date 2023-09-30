package com.campusdual.model.dao;

import com.campusdual.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    // Conecta con hibernate para ejecutar las consultas que se definen:

    // Tiene que empezar por find y poner el campo y el metodo de busqueda
    // Consultar creacion de consultas en Spring
    List<Product> findByNameLike(String name);
    List<Product> findByStock(Integer stock);
    List<Product> findByActiveTrue();
    List<Product> findByStockGreaterThan(Integer stock);

}
