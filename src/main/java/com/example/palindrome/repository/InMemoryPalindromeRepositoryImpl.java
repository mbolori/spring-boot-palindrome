package com.example.palindrome.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.Palindrome;
import com.google.common.base.Strings;

/**
 * @author gzml7g
 *
 */
@Repository
public class InMemoryPalindromeRepositoryImpl implements PalindromeRepository{

    /** Map is not threadsabe but it is made of immutable Palindrome. Besides composite operations requires held proper method */
    private Map<String, Palindrome> storage = new HashMap<>();
    
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    private final Lock r = lock.readLock();
    
    private final Lock w = lock.writeLock();
    
    private final Logger log = LoggerFactory.getLogger(InMemoryPalindromeRepositoryImpl.class);
    
    public InMemoryPalindromeRepositoryImpl() {
    }
    /**
     * @see com.example.palindrome.repository.PalindromeRepository#store(com.example.palindrome.domain.CheckPalindromeRequest)
     *
     * @param request
     * @return
     */
    @Override
    public Palindrome store(CheckPalindromeRequest request) {
        w.lock();
        try {
            Palindrome palindrome 
                        = Palindrome.fromRequest(request)
                            .setId(UUID.randomUUID().toString())
                            .setDate(new Date())
                                .build();
            
            storage.put(palindrome.getId(), palindrome);
            log.info("Stored Palindrome on map: " + palindrome);
            return palindrome;
        } finally {
            w.unlock();
        }
    }

    /**
     * @see com.example.palindrome.repository.PalindromeRepository#getAllPalindrome()
     *
     * @return
     */
    @Override
    public Collection<Palindrome> getAllPalindrome() {
        r.lock();
        try {
            log.info("Retrieving all pallindromes from map. Size: " + storage.size());
            return Collections.unmodifiableCollection(storage.values());
        } finally {
            r.unlock();
        }
    }

    /**
     * @see com.example.palindrome.repository.PalindromeRepository#getPalindromeById(java.lang.String)
     *
     * @param id
     * @return
     */
    @Override
    public Palindrome getPalindromeById(String id) {
        r.lock(); 
        try {
            if (Strings.isNullOrEmpty(id))
                return null;
            
            log.info("Retrieving palindrome by id from map.");
            return storage.get(id);
        } finally {
            r.unlock();
        }
    }
    /**
     * @see com.example.palindrome.repository.PalindromeRepository#remove(java.lang.String)
     *
     * @param id
     */
    @Override
    public Palindrome remove(String id) {
       w.lock();
       try {
           log.info("Removing palindrome by id from map.");
           return storage.remove(id);
       } finally {
           w.unlock();
       }
        
    }
    /**
     * @see com.example.palindrome.repository.PalindromeRepository#removeAll()
     *
     */
    @Override
    public void removeAll() {
        w.lock();
        try {
            log.info("Removing all palindrome from map.");
            storage.clear();  
        } finally {
            w.unlock();
        }     
    }
}
