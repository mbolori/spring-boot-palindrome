package com.example.palindrome.service;

/**
 * It wraps error conditions on PalindromeService service interface.
 * There are several causes implemented:
 *    NoText: Text is missing: unable to check whether is a palindrome or not.
 *    PalindromeIdExpected: palindromeId missing.
 *    PalindromeNotFound: Unable to look up palindrome
 */
public class PalindromeServiceException extends Exception{

    private static final long serialVersionUID = -7891469761272460048L;

    /**
     * The Enum Causes.
     */
    public static enum Causes {
        /** The No text. */
        NoText, 
        /** The Palindrome id expected. */
        PalindromeIdExpected, 
        /** The Palindrome not found. */
        PalindromeNotFound};
    
    private Causes cause;
    
    /**
     * Instantiates a new palindrome service exception.
     *
     * @param errMsg the err msg
     */
    public PalindromeServiceException(String errMsg) {
        super(errMsg);
    }
    
    /**
     * Instantiates a new palindrome service exception.
     *
     * @param errMsg the err msg
     * @param cause the cause
     */
    public PalindromeServiceException(String errMsg, Causes cause) {
        super(errMsg);
        this.cause = cause;
    }
    
    /**
     * Instantiates a new palindrome service exception.
     *
     * @param errMsg the err msg
     * @param excp the excp
     */
    public PalindromeServiceException(String errMsg, Throwable excp) {
        super(errMsg, excp);
    }
    
    /**
     * Gets the exception cause.
     *
     * @return the exception cause
     */
    public Causes getExceptionCause() {
        return this.cause;
    }

    /**
     * @see java.lang.Throwable#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PalindromeServiceException [cause=").append(cause).append("]");
        return builder.toString();
    }
    
}
