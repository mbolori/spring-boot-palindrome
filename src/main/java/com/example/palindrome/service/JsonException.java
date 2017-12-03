package com.example.palindrome.service;

/**
 *
 */
public class JsonException extends Exception{

    private static final long serialVersionUID = -2128205464371867640L;

    public JsonException() {
    }
    
    public JsonException(String errMsg) {
        super(errMsg);
    }
    
    public JsonException(String errMsg, Throwable e) {
        super(errMsg, e);
    }
}
