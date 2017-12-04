package com.example.palindrome;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * To switch off the default web application security configuration completely you can add a bean with @EnableWebSecurity
 * This does not disable the authentication manager configuration or Actuator’s security.
 * To customize it you normally use external properties and beans of type WebSecurityConfigurerAdapter.
 * 
 * To also switch off the authentication manager configuration you can add a bean of type AuthenticationManager, or else configure the global AuthenticationManager 
 * by autowiring an AuthenticationManagerBuilder
 *
 */
@Configuration
@EnableWebSecurity
public class BasicWebSecurityConfiguration extends WebSecurityConfigurerAdapter   {
    
    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/spring-boot-palindrome/api/**").hasRole("USER")    //authentication: all endpoints require authorized user having "USER" role
        	.and()
        		.httpBasic() // all endpoints will be secured using http basic  Authorization: Basic base64-encoding of username:password,
        	.and()
        		.sessionManagement()  // prevent Spring security to create a session: force client to add http basic authorization header on all the request ..
        			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        		.headers()      // Adds the Security headers to the response.
        		.frameOptions() // Allows customizing the XFrameOptionsHeaderWriter
        		.sameOrigin()   // Specify to allow any request that comes from the same origin to frame this application.
        	.and()          
        		.csrf()         // disable CSRF support
        			.disable();
    }
    
    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //fill in memory authentiocation Manager with an user 
            auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
    
}
