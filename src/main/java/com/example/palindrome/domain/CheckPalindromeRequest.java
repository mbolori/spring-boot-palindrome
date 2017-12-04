package com.example.palindrome.domain;

import java.io.IOException;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Inbound Json content used to check whether a text is a palindrome or not..
 * It follows Java Beans convention to perform Json to POJO deserialization.
 *
 */
public class CheckPalindromeRequest {

    @JsonProperty("text")
    private String text;
    
    @JsonProperty("user")
    private String user;
    
    /**
     * Instantiates a new check palindrome request.
     */
    public CheckPalindromeRequest() {
    }
    
    /**
     * Deserialize Json into CheckPalindromeRequest POJO. 
     *
     * @param json the json
     * @return CheckPalindromeRequest
     * @throws JsonException - In case of error during deserialization.
     */
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

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckPalindromeRequest [text=").append(text).append(", user=").append(user).append("]");
        return builder.toString();
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(String user) {
        this.user = user;
    }

}
