package com.weddly.bestmatchedrestaurantswr.exception;

public class InvalidParameterException extends RuntimeException {
    private String message;

    public InvalidParameterException(String message) {
        super("Error: " + message);
    }
    public InvalidParameterException(){}
    
}
