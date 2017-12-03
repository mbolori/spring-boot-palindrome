package com.example.palindrome.service.strategy.impl;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 *
 */
public class PalindromeStrategyOptimum implements PalindromeStrategy{

    /**
     * @see com.example.palindrome.service.strategy.PalindromeStrategy#isPalindrome(java.lang.String)
     *
     * @param text
     * @return
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
    
    @Override
    public String toString() {
        return "OPTIMUM Strategy";
    }

}
