package com.example.palindrome.service.strategy.impl;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 * The Class PalindromeStrategyOptimum implements PalindromeStrategy in the most optimum way.
 */
public class PalindromeStrategyOptimum implements PalindromeStrategy{

    /**
     * Checks if is palindrome.
     *
     * @param text the text
     * @return true, if is palindrome
     * @see com.example.palindrome.service.strategy.PalindromeStrategy#isPalindrome(java.lang.String)
     */
    @Override
    public boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        return "OPTIMUM Strategy";
    }

}
