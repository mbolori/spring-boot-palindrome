package com.example.palindrome.service.api;

import java.util.Collection;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.CheckPalindromeResponse;
import com.example.palindrome.domain.Palindrome;
import com.example.palindrome.service.PalindromeServiceException;

/**
 * The Interface PalindromeService handle service layer palindrome request
 * Domain-Driven design service layer.
 */
public interface PalindromeService {

    /**
     * Check if palindrome.
     *
     * @param request the request
     * @return the check palindrome response
     * @throws PalindromeServiceException the palindrome service exception
     */
    public CheckPalindromeResponse checkIfPalindrome(CheckPalindromeRequest request) throws PalindromeServiceException;
    
    /**
     * Gets the all palindrome.
     *
     * @return the all palindrome
     */
    public Collection<Palindrome> getAllPalindrome();
    
    /**
     * Gets the palindrome by id.
     *
     * @param id the id
     * @return the palindrome by id
     * @throws PalindromeServiceException the palindrome service exception
     */
    public Palindrome getPalindromeById(String id) throws PalindromeServiceException;
    
    /**
     * Removes the all palindrome.
     */
    public void removeAllPalindrome();
    
    /**
     * Removes the palindrome by id.
     *
     * @param id the id
     * @throws PalindromeServiceException the palindrome service exception
     */
    public void removePalindromeById(String id) throws PalindromeServiceException;
    
}
