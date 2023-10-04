package com.cantina.cantina.services.exceptions;

public class NullNotAllowedException extends RuntimeException{

    public NullNotAllowedException(Object id){
        super("Null field not allowed!");
    }
}
