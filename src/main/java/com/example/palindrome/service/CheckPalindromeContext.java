package com.example.palindrome.service;

import com.example.palindrome.service.strategy.PalindromeStrategy;

/**
 * The Class CheckPalindromeContext is a implementation of Strategy pattern:
 *    It is instantiated with a strategy, it will delegate to that strategy the palindrome check.
 */
public class CheckPalindromeContext {

    private PalindromeStrategy strategy;
    
    /**
     * Instantiates a new check palindrome context.
     *
     * @param strategy the strategy
     */
    public CheckPalindromeContext(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Checks if is palindrome.
     *
     * @param text the text
     * @return true, if is palindrome
     */
    public boolean isPalindrome(String text) {
        return strategy.isPalindrome(text);
    }
    
    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckPalindromeContext [using strategy").append(strategy).append("]");
        return builder.toString();
    }
    
}
