package com.example.palindrome.service.api;

import java.util.Collection;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.CheckPalindromeResponse;
import com.example.palindrome.domain.Palindrome;
import com.example.palindrome.service.PalindromeServiceException;

/**
 * 
 */
public interface PalindromeService {

    public CheckPalindromeResponse checkIfPalindrome(CheckPalindromeRequest request) throws PalindromeServiceException;
    
    public Collection<Palindrome> getAllPalindrome();
    
    public Palindrome getPalindromeById(String id) throws PalindromeServiceException;
    
    public void removeAllPalindrome();
    
    public void removePalindromeById(String id) throws PalindromeServiceException;
    
}
