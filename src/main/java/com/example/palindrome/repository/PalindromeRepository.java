package com.example.palindrome.repository;

import java.util.Collection;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.Palindrome;

/**
 *
 */
public interface PalindromeRepository {

    public Palindrome store(CheckPalindromeRequest request);
    
    public Collection<Palindrome> getAllPalindrome();
    
    public Palindrome getPalindromeById(String id);
    
    public Palindrome remove(String id);
    
    public void removeAll();
}
