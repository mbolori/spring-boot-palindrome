package com.example.palindrome.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.palindrome.configuration.PalindromeConfiguration;
import com.example.palindrome.configuration.PalindromeConfiguration.Strategies;
import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.CheckPalindromeResponse;
import com.example.palindrome.domain.Palindrome;
import com.example.palindrome.repository.PalindromeRepository;
import com.example.palindrome.service.CheckPalindromeContext;
import com.example.palindrome.service.PalindromeServiceException;
import com.example.palindrome.service.PalindromeServiceException.Causes;
import com.example.palindrome.service.api.PalindromeService;
import com.example.palindrome.service.strategy.PalindromeStrategy;
import com.example.palindrome.service.strategy.impl.PalindromeStrategyFullString;
import com.example.palindrome.service.strategy.impl.PalindromeStrategyOptimum;
import com.google.common.base.Strings;

/**
 * @author gzml7g
 *
 */
@Service
public class PalindromeServiceImpl implements PalindromeService{

    private final Logger log = LoggerFactory.getLogger(PalindromeServiceImpl.class);
    
    @Autowired
    PalindromeConfiguration config;
    
    @Autowired
    PalindromeRepository repository;
    
    /**
     * @see com.example.palindrome.service.api.PalindromeService#isPalindrome()
     *
     * @return
     */
    @Override
    public CheckPalindromeResponse checkIfPalindrome(CheckPalindromeRequest request) throws PalindromeServiceException {
        log.info("Using strategy: " + config.getStrategy());
        
        if (Strings.isNullOrEmpty(request.getText()))
            throw new PalindromeServiceException("Text is empty", Causes.NoText);
        
        PalindromeStrategy strategy = config.getStrategy() == Strategies.OPTIMUM ? new PalindromeStrategyOptimum() : new PalindromeStrategyFullString();
        boolean isPalindrome = new CheckPalindromeContext(strategy).isPalindrome(request.getText());
             
        if (isPalindrome) {
            log.info("Text:" + request.getText() + " is palindrome, storing. ");
            Palindrome palindrome = repository.store(request);
            return new CheckPalindromeResponse(true, request.getText(), palindrome.getId());          
        } else {
            log.info("Text: " + request.getText() + " is not palindrome");
            return new CheckPalindromeResponse(false, request.getText());
        }
    }

    /**
     * @see com.example.palindrome.service.api.PalindromeService#getAllPalindrome()
     *
     */
    @Override
    public Collection<Palindrome> getAllPalindrome() {
        log.info("Retrieving all palindrome");
        return repository.getAllPalindrome();
    }

    /**
     * @see com.example.palindrome.service.api.PalindromeService#getPalindromeById(java.lang.String)
     *
     * @param id
     * @throws PalindromeServiceException 
     */
    @Override
    public Palindrome getPalindromeById(String id) throws PalindromeServiceException {
        log.info("Retrieving palindrome.");
        
        if (Strings.isNullOrEmpty(id))
            throw new PalindromeServiceException("Expected Palindrome Id.", Causes.PalindromeIdExpected);
        
        Palindrome palindrome = repository.getPalindromeById(id);
        if (palindrome == null) {
            throw new PalindromeServiceException("There is no Palindrome using that Id.", Causes.PalindromeNotFound);
        }
        return palindrome;
    }

    /**
     * @see com.example.palindrome.service.api.PalindromeService#deleteAllPalindrome()
     *
     */
    @Override
    public void removeAllPalindrome() {
        log.info("Delete all palindrome.");
        repository.removeAll();
    }

    /**
     * @throws PalindromeServiceException 
     * @see com.example.palindrome.service.api.PalindromeService#deletePalindromeById()
     *
     */
    @Override
    public void removePalindromeById(String id) throws PalindromeServiceException {
        log.info("Delete palindrome.");
        
        if (Strings.isNullOrEmpty(id))
            throw new PalindromeServiceException("Expected Palindrome Id.", Causes.PalindromeIdExpected);
        
        if (repository.remove(id) == null) {
            throw new PalindromeServiceException("Unable to delete: There is no Palindrome using that Id.", Causes.PalindromeNotFound);
        }
        
    }

}
