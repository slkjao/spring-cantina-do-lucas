package com.cantina.cantina.services.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Resource not found! ID: "+ id);
    }
}
