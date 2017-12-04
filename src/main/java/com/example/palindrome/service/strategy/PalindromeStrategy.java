package com.example.palindrome.service.strategy;

/**
 * The Interface PalindromeStrategy 
 * It is strategy component within  strategy pattern.
 */
public interface PalindromeStrategy {
    
    /**
     * Checks if is palindrome.
     *
     * @param text the text
     * @return true, if is palindrome
     */
    public boolean isPalindrome(String text);

}
