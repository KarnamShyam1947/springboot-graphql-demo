package com.shyam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entities.ProductEntity;
import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategory(String category);
}
