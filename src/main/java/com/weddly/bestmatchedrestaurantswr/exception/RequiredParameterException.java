package com.weddly.bestmatchedrestaurantswr.exception;

public class RequiredParameterException extends RuntimeException {
    private String message;

    public RequiredParameterException(String param) {
        super("Error: " + param + " is a required parameter");
    }
    public RequiredParameterException(){}
    
}
