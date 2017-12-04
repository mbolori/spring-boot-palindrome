package com.example.palindrome.service;

/**
 * It wraps all IOException during Jackson serialization-deserialization
 */
public class JsonException extends Exception{

    private static final long serialVersionUID = -2128205464371867640L;

    /**
     * Instantiates a new json exception.
     */
    public JsonException() {
    }
    
    /**
     * Instantiates a new json exception.
     *
     * @param errMsg the err msg
     */
    public JsonException(String errMsg) {
        super(errMsg);
    }
    
    /**
     * Instantiates a new json exception.
     *
     * @param errMsg the err msg
     * @param e the e
     */
    public JsonException(String errMsg, Throwable e) {
        super(errMsg, e);
    }
}
