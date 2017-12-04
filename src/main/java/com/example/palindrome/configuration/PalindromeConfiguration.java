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

    /**
     * The Enum Strategies.
     */
    public static enum Strategies { /** The optimum. */
 OPTIMUM, /** The full string. */
 FULL_STRING };
    
    private Strategies strategy = Strategies.OPTIMUM;
    
    private boolean remoteConfiguration = false;
    
    private boolean enableSecurity = false;
    
    /**
     * Instantiates a new palindrome configuration.
     */
    public PalindromeConfiguration() {
    }

    /**
     * Gets the strategy.
     *
     * @return the strategy
     */
    public Strategies getStrategy() {
        return strategy;
    }

    /**
     * Sets the strategy.
     *
     * @param strategy the new strategy
     */
    public void setStrategy(Strategies strategy) {
        this.strategy = strategy;
    }

    /**
     * Checks if is remote configuration.
     *
     * @return true, if is remote configuration
     */
    public boolean isRemoteConfiguration() {
        return remoteConfiguration;
    }

    /**
     * Sets the remote configuration.
     *
     * @param remoteConfiguration the new remote configuration
     */
    public void setRemoteConfiguration(boolean remoteConfiguration) {
        this.remoteConfiguration = remoteConfiguration;
    }

    /**
     * Checks if is enable security.
     *
     * @return true, if is enable security
     */
    public boolean isEnableSecurity() {
        return enableSecurity;
    }

    /**
     * Sets the enable security.
     *
     * @param enableSecurity the new enable security
     */
    public void setEnableSecurity(boolean enableSecurity) {
        this.enableSecurity = enableSecurity;
    }

    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PalindromeConfiguration [strategy=").append(strategy).append(", remoteConfiguration=").append(remoteConfiguration)
                .append(", enableSecurity=").append(enableSecurity).append("]");
        return builder.toString();
    }
    
}
