package com.shyam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shyam.entities.ProductEntity;
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

    //sales team : update the stock of a product in (IS)
    public ProductEntity updateStock(int id, int stock){

       ProductEntity existingProduct= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found with id "+id));

       existingProduct.setStock(stock);
       return repository.save(existingProduct);
    }

    //warehouse : receive new shipment
    public ProductEntity receiveNewShipment(int id, int quantity){

        ProductEntity existingProduct= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found with id "+id));

       existingProduct.setStock(existingProduct.getStock()+quantity);
        return repository.save(existingProduct);
    }
}
