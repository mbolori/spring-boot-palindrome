package com.example.palindrome.domain;

import java.io.IOException;
import java.io.StringWriter;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Outbound Json content containing palindrome check result.
 * It follows Java Beans convention to perform POJO to JSON serialization.
 *
 */
public class CheckPalindromeResponse{

    @JsonProperty("palindrome")
    private boolean palindrome = false;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("text")
    private String text;
    
    /**
     * Instantiates a new check palindrome response.
     *
     * @param palindrome the palindrome
     * @param text the text
     */
    public CheckPalindromeResponse(boolean palindrome, String text) {
        this.palindrome = palindrome;
        this.text = text;
    }
    
    /**
     * Instantiates a new check palindrome response.
     *
     * @param palindrome the palindrome
     * @param text the text
     * @param id the id
     */
    public CheckPalindromeResponse(boolean palindrome, String text, String id) {
        this(palindrome, text);
        this.id = id;
    }
    
    /**
     * Checks if is palindrome.
     *
     * @return true, if is palindrome
     */
    public boolean isPalindrome() {
        return palindrome;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
     * Serialize CheckPalindromeResponse POJO into JSON.
     *
     * @return the string
     * @throws JsonException - In case of error during serialization.
     */
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
    
    /**
     * @see java.lang.Object#toString()
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append("CheckPalindromeResponse [palindrome=").append(palindrome).append(", id=").append(id).append(", text=").append(text).append("]");
        return builder2.toString();
    }

    /**
     * Sets the palindrome.
     *
     * @param palindrome the new palindrome
     */
    public void setPalindrome(boolean palindrome) {
        this.palindrome = palindrome;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }
 
}
