package com.shyam.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.shyam.entities.ProductEntity;
import com.shyam.exceptions.ProductExistsException;
import com.shyam.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @QueryMapping
    public List<ProductEntity> getProducts() {
        return service.getProducts();
    }

    @QueryMapping
    public List<ProductEntity> getProductsByCategory(@Argument String category) {
        return service.getProductsByCategory(category);
    }


    @MutationMapping
    public ProductEntity updateStock(@Argument int id, @Argument int stock) {
        return service.updateStock(id, stock);

    }

    @MutationMapping
    public ProductEntity receiveNewShipment(@Argument int id, @Argument int quantity) {
        return service.receiveNewShipment(id, quantity);

    }
    
    @MutationMapping
    public ProductEntity createProduct(
        @Argument String name,
        @Argument String category,
        @Argument Float price,
        @Argument Integer stock
    ) throws ProductExistsException {
        return service.addProduct(name, category, price, stock);
    }
}
