/*******************************************************************************
* File : PalindromeCheckConfigRunner.java<br>
* created at : Nov 30, 2017<br>
* created by : gzml7g<br>
******************************************************************************/
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
 * Check config or just show configuration loaded ...
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PalindromeCheckConfigRunner implements ApplicationRunner{

    private final static Logger log = LoggerFactory.getLogger(PalindromeCheckConfigRunner.class);

    @Autowired
    PalindromeConfiguration config;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Checking config ...");
        log.info("Configuration file loaded: " +  config);
    }

}
