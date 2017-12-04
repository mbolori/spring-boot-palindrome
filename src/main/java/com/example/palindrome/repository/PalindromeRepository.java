package com.example.palindrome.repository;

import java.util.Collection;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.Palindrome;

/**
 * The Interface PalindromeRepository to handle Palindrome storage
 * Domain-Driven design repository layer.
 */
public interface PalindromeRepository {

    /**
     * Store confirmed Palindrome into storage area (out of a CheckPalindromeRequest).
     *
     * @param request the request
     * @return the palindrome
     */
    public Palindrome store(CheckPalindromeRequest request);
    
    /**
     * Gets all palindrome from storage.
     *
     * @return the all palindrome
     */
    public Collection<Palindrome> getAllPalindrome();
    
    /**
     * Gets the palindrome by id.
     *
     * @param id the id
     * @return the palindrome by id
     */
    public Palindrome getPalindromeById(String id);
    
    /**
     * Removes palindrome by palindromeId.
     *
     * @param id the id
     * @return the palindrome
     */
    public Palindrome remove(String id);
    
    /**
     * Removes all palindrome.
     */
    public void removeAll();
    
    /**
     * Checks if is stored a palindrome .
     *
     * @param text the text
     * @return the palindrome
     */
    public Palindrome isStored(String text);
}
