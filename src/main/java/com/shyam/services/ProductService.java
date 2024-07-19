package com.shyam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shyam.entities.ProductEntity;
import com.shyam.exceptions.ProductExistsException;
import com.shyam.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository repository;

    public List<ProductEntity> getProducts(){
        return repository.findAll();
    }

    public List<ProductEntity> getProductsByCategory(String category){
        return repository.findByCategory(category);
    }

    public ProductEntity addProduct(
        String name,
        String category,
        Float price,
        Integer stock
    ) throws ProductExistsException {
        ProductEntity product = repository.findByName(name);
        if (product != null) {
            throw new ProductExistsException("Product already exists with same name : " + name);
        }
        ProductEntity newProduct = ProductEntity
                                    .builder()
                                    .name(name)
                                    .price(price)
                                    .stock(stock)
                                    .category(category)
                                    .build();
                                    
        return repository.save(newProduct);
    }

    public ProductEntity updateStock(int id, int stock){

       ProductEntity existingProduct= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found with id "+id));

       existingProduct.setStock(stock);
       return repository.save(existingProduct);
    }

    public ProductEntity receiveNewShipment(int id, int quantity){

        ProductEntity existingProduct= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found with id "+id));

       existingProduct.setStock(existingProduct.getStock()+quantity);
        return repository.save(existingProduct);
    }
}
