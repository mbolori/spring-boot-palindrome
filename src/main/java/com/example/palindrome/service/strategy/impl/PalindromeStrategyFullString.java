package com.example.palindrome.service.strategy.impl;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 *
 */
public class PalindromeStrategyFullString implements PalindromeStrategy {

    /**
     * @see com.example.palindrome.service.strategy.PalindromeStrategy#isPalindrome(java.lang.String)
     *
     * @param text
     * @return
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

    @Override
    public String toString() {
        return "FULL_STRING Strategy";
    }
    
}
