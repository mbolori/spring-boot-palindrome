package com.example.palindrome.domain;

import java.io.IOException;
import java.io.StringWriter;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CheckPalindromeResponse{

    @JsonProperty("palindrome")
    private boolean palindrome = false;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("text")
    private String text;
    
    public CheckPalindromeResponse(boolean palindrome, String text) {
        this.palindrome = palindrome;
        this.text = text;
    }
    
    public CheckPalindromeResponse(boolean palindrome, String text, String id) {
        this(palindrome, text);
        this.id = id;
    }
    
    public boolean isPalindrome() {
        return palindrome;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String toJson() throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw,this);
            return sw.toString();
        } catch (IOException e) {
            throw new JsonException("Error marshalling " +  this, e);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append("CheckPalindromeResponse [palindrome=").append(palindrome).append(", id=").append(id).append(", text=").append(text).append("]");
        return builder2.toString();
    }

    public void setPalindrome(boolean palindrome) {
        this.palindrome = palindrome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
 
}
