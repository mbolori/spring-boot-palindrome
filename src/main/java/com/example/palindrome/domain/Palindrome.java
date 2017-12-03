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
    
    public Palindrome() {
    }
    
    public Palindrome(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.user = builder.user;
        this.date = builder.date;
    }
    
    public static class Builder {
        
        private String id;
        
        private String text;
        
        private String user;
        
        private Date date;
        
        public Builder(String text, String user) {
            this.text = text;
            this.user = user;
        }
        
        public Builder setId(String id) {
            this.id = id;
            return this;
        }
        
        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }
        
        public Palindrome build() {
            return new Palindrome(this);
        }

    }
    
    public static Palindrome.Builder fromRequest(CheckPalindromeRequest request) {
        return new Palindrome.Builder(request.getText(), request.getUser());
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
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
    
    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append("Palindrome [id=").append(id).append(", text=").append(text).append(", user=").append(user).append(", date=").append(date).append("]");
        return builder2.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
