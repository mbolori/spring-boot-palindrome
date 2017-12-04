package com.example.palindrome.service.strategy.impl;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 * The Class PalindromeStrategyOptimum implements PalindromeStrategy reversing text and comparing original against reversed one.
 */
public class PalindromeStrategyFullString implements PalindromeStrategy {

    /**
     * Checks if is palindrome.
     *
     * @param text the text
     * @return true, if is palindrome
     * @see com.example.palindrome.service.strategy.PalindromeStrategy#isPalindrome(java.lang.String)
     */
    @Override
    public boolean isPalindrome(String text) {
        if (text == null)
            return false;
        
        String reverseText = new StringBuilder(text).reverse().toString();
        if (text.equals(reverseText))
            return true;
        else
            return false;
    }

    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        return "FULL_STRING Strategy";
    }
    
}
