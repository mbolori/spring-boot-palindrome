package com.example.palindrome.service;

/**
 *
 */
public class PalindromeServiceException extends Exception{

    private static final long serialVersionUID = -7891469761272460048L;

    public static enum Causes {NoText, PalindromeIdExpected, PalindromeNotFound};
    
    private Causes cause;
    
    public PalindromeServiceException(String errMsg) {
        super(errMsg);
    }
    
    public PalindromeServiceException(String errMsg, Causes cause) {
        super(errMsg);
        this.cause = cause;
    }
    
    public PalindromeServiceException(String errMsg, Throwable excp) {
        super(errMsg, excp);
    }
    
    public Causes getExceptionCause() {
        return this.cause;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PalindromeServiceException [cause=").append(cause).append("]");
        return builder.toString();
    }
    
}
