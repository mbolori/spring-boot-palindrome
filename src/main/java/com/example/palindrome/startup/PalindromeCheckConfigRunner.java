package com.example.palindrome.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.palindrome.configuration.PalindromeConfiguration;


/**
 * Display application specific configuration.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PalindromeCheckConfigRunner implements ApplicationRunner{

    private final static Logger log = LoggerFactory.getLogger(PalindromeCheckConfigRunner.class);

    /** The config. */
    @Autowired
    PalindromeConfiguration config;
    
    /**
     * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Checking config ...");
        log.info("Configuration file loaded: " +  config);
    }

}
