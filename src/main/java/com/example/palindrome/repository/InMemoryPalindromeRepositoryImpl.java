package com.example.palindrome.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
 * In memory representation of a PalindromeRepository.
 * Storage is implemented as a Map. Map is not thread-safe but it is made of immutable Palindrome. Besides composite operations require held proper lock
 * therefore implementation can be considered thread-safe.
 * Write operations (store, remove, removeAll) held write lock. Only one can held that lock. Read locks are forbidden during duration of those operations.
 * Read operations  (isStored, getAllPalindrome, getPalindromeById) held read lock. Many read locks at the same time are allowed.
 * Domain-Driven design repository layer.
 */
@Repository
public class InMemoryPalindromeRepositoryImpl implements PalindromeRepository{

    private Map<String, Palindrome> storage = new HashMap<>();
    
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    private final Lock r = lock.readLock();
    
    private final Lock w = lock.writeLock();
    
    private final Logger log = LoggerFactory.getLogger(InMemoryPalindromeRepositoryImpl.class);
    
    /**
     * Instantiates a new in memory palindrome repository impl.
     */
    public InMemoryPalindromeRepositoryImpl() {
    }
    
    /**
     * Store.
     *
     * @param request the request
     * @return the palindrome
     * @see com.example.palindrome.repository.PalindromeRepository#store(com.example.palindrome.domain.CheckPalindromeRequest)
     */
    @Override
    public Palindrome store(CheckPalindromeRequest request) {
        w.lock();
        Palindrome palindrome;
        try {
        	//CHECK this palindrome is not already stored ...
        	if ((palindrome = isStored(request.getText())) != null) {
        		log.info("Already stored on map.");
        		return palindrome;
        	} else {
                palindrome 
                	= Palindrome.fromRequest(request)
                    	.setId(UUID.randomUUID().toString())
                    	.setDate(new Date())
                        .build();
    
                storage.put(palindrome.getPalindromeId(), palindrome);
                log.info("Stored Palindrome on map: " + palindrome);
                return palindrome;
        	}
        } finally {
            w.unlock();
        }
    }

    /**
     * @see com.example.palindrome.repository.PalindromeRepository#isStored(java.lang.String)
     *
     * @param text
     * @return
     */
    @Override
    public Palindrome isStored(String text) {
    	for (Entry<String, Palindrome> entry : storage.entrySet()) {
    		if (text.equals(entry.getValue().getText()))
    			return entry.getValue();
    	}
    	return null;
    }
    
    /**
     * Gets the all palindrome.
     *
     * @return the all palindrome
     * @see com.example.palindrome.repository.PalindromeRepository#getAllPalindrome()
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
     * Gets the palindrome by id.
     *
     * @param id the id
     * @return the palindrome by id
     * @see com.example.palindrome.repository.PalindromeRepository#getPalindromeById(java.lang.String)
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
     * Removes the.
     *
     * @param id the id
     * @return the palindrome
     * @see com.example.palindrome.repository.PalindromeRepository#remove(java.lang.String)
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
     * Removes the all.
     *
     * @see com.example.palindrome.repository.PalindromeRepository#removeAll()
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
