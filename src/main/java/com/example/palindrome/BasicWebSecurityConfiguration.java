package com.example.palindrome;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .headers()      // Adds the Security headers to the response.
        .frameOptions() // Allows customizing the XFrameOptionsHeaderWriter
        .sameOrigin()   // Specify to allow any request that comes from the same origin to frame this application.
        .and()          
        .csrf()         // disable CSRF support
        .disable();
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//            .httpBasic();
//    }
// 
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("manolo").password("manolo").roles("USER", "ADMIN")
//            .and()
//            .withUser("otherUser").password("otherUser").roles("USER", "ADMIN");
//    }
    
}
