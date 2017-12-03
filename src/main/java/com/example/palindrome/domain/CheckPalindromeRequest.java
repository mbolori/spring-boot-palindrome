package com.example.palindrome.domain;

import java.io.IOException;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gzml7g
 *
 */
public class CheckPalindromeRequest {

    @JsonProperty("text")
    private String text;
    
    @JsonProperty("user")
    private String user;
    
    public CheckPalindromeRequest() {
    }
    
    public static CheckPalindromeRequest fromJson(String json) throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {            
            return mapper.readValue(json, CheckPalindromeRequest.class);
        } catch (IOException e) {
            throw new JsonException("Error unmarshalling json: " + e);
        }
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckPalindromeRequest [text=").append(text).append(", user=").append(user).append("]");
        return builder.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
