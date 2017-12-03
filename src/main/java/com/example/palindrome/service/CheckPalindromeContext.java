package com.example.palindrome.service;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 * @author gzml7g
 *
 */
public class CheckPalindromeContext {

    private PalindromeStrategy strategy;
    
    public CheckPalindromeContext(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }
    
    public boolean isPalindrome(String text) {
        return strategy.isPalindrome(text);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckPalindromeContext [using strategy").append(strategy).append("]");
        return builder.toString();
    }
    
}
