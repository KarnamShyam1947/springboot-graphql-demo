package com.shyam.controllers;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.shyam.exceptions.ProductExistsException;

import graphql.GraphQLError;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @GraphQlExceptionHandler
    public GraphQLError handleException(ProductExistsException e) {
        return GraphQLError
                .newError()
                .message(e.getMessage())
                .build();
    }
}