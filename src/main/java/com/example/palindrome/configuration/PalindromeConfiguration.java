package com.example.palindrome.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Spring Configuration class: Spring Boot takes care of filling fields prefixed with "palindromeConfiguration"
 * Follows Java Beans rules to make Spring match class fields with yaml properties. 
 */
@Component
@ConfigurationProperties(prefix="palindromeConfiguration")
public class PalindromeConfiguration {

    public static enum Strategies { OPTIMUM, FULL_STRING };
    
    private Strategies strategy = Strategies.OPTIMUM;
    
    private boolean remoteConfiguration = false;
    
    private boolean enableSecurity = false;
    
    public PalindromeConfiguration() {
    }

    public Strategies getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategies strategy) {
        this.strategy = strategy;
    }

    public boolean isRemoteConfiguration() {
        return remoteConfiguration;
    }

    public void setRemoteConfiguration(boolean remoteConfiguration) {
        this.remoteConfiguration = remoteConfiguration;
    }

    public boolean isEnableSecurity() {
        return enableSecurity;
    }

    public void setEnableSecurity(boolean enableSecurity) {
        this.enableSecurity = enableSecurity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PalindromeConfiguration [strategy=").append(strategy).append(", remoteConfiguration=").append(remoteConfiguration)
                .append(", enableSecurity=").append(enableSecurity).append("]");
        return builder.toString();
    }
    
}
