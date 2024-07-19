package com.shyam.exceptions;

public class ProductExistsException extends Exception { 
    public ProductExistsException() {
        super("Product Already exists");
    }
    
    public ProductExistsException(String str) {
        super(str);
    }
}