package com.example.palindrome.domain;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * It stores all information for a palindrome:
 *   id - identifier to look up a palindrome
 *   text - palindrome's text
 *   user - which user added that palindrome
 *   date - when was added the palindrome
 *     
 */
public class Palindrome {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("user")
    private String user;
    
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private Date date;
    
    /**
     * Instantiates a new palindrome.
     */
    public Palindrome() {
    }
    
    /**
     * Instantiates a new palindrome.
     *
     * @param builder the builder
     */
    public Palindrome(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.user = builder.user;
        this.date = builder.date;
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        private String id;
        
        private String text;
        
        private String user;
        
        private Date date;
        
        /**
         * Instantiates a new builder.
         *
         * @param text the text
         * @param user the user
         */
        public Builder(String text, String user) {
            this.text = text;
            this.user = user;
        }
        
        /**
         * Sets the id.
         *
         * @param id the id
         * @return the builder
         */
        public Builder setId(String id) {
            this.id = id;
            return this;
        }
        
        /**
         * Sets the date.
         *
         * @param date the date
         * @return the builder
         */
        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }
        
        /**
         * Builds the.
         *
         * @return the palindrome
         */
        public Palindrome build() {
            return new Palindrome(this);
        }

    }
    
    /**
     * From request. Crete a Palindrome' Builder out of a CheckPalindromeRequest request.
     *
     * @param request the request
     * @return the palindrome. builder
     */
    public static Palindrome.Builder fromRequest(CheckPalindromeRequest request) {
        return new Palindrome.Builder(request.getText(), request.getUser());
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
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Serialize Palindrome POJO into JSON 
     *
     * @return the string
     * @throws JsonException - exception during serialization.
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
     * Serialize a Collection<Palindrome> into JSON (array of elements).
     *
     * @param palindromeCollection the palindrome collection
     * @return the string
     * @throws JsonException - exception during serialization.
     */
    public static String toJsonCollection(Collection<Palindrome> palindromeCollection) throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        
        StringWriter sw = new StringWriter();
        try {
            mapper.writerFor(new TypeReference<Collection<Palindrome>>(){}).writeValue(sw, palindromeCollection);
            return sw.toString();
        } catch (IOException e) {
            throw new JsonException("Error marshalling " +  palindromeCollection, e);
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
        builder2.append("Palindrome [id=").append(id).append(", text=").append(text).append(", user=").append(user).append(", date=").append(date).append("]");
        return builder2.toString();
    }
    
}
