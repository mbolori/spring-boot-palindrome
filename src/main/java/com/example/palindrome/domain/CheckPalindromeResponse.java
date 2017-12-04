package com.example.palindrome.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Outbound Json content containing palindrome check result.
 * It follows Java Beans convention to perform POJO to JSON serialization.
 *
 *  links : [ { rel : "self",   href : "http://localhost:9100/spring-boot-palindrome/api/palindromes/<id>" },
 *            { rel : "delete_palindrome_<id>", href : "http://localhost:9100/spring-boot-palindrome/api/palindromes/<id>" },  
 *            { rel : "delete_palindrome_all", href : "http://localhost:9100/spring-boot-palindrome/api/palindromes" },
 *            { rel : "get_palindrome_all", href : "http://localhost:9100/spring-boot-palindrome/api/palindromes" },
 *  ]
 */
public class CheckPalindromeResponse extends ResourceSupport {

    @JsonProperty("palindrome")
    private boolean palindrome = false;
    
    @JsonProperty("palindromeId")
    private String palindromeId;
    
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
        this.palindromeId = id;
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
    public String getPalindromeId() {
        return palindromeId;
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
        setupLinks();
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
    
    protected void setupLinks() {
        if (this.isPalindrome()) {
            //enrich with links
            Link selfLink =  linkTo(JaxRsLinkBuilder.class).slash(this.getPalindromeId()).withSelfRel();            
            Link getAllLink = linkTo(JaxRsLinkBuilder.class).withRel("get_palindrome_all");
            Link deleteLink = linkTo(JaxRsLinkBuilder.class).slash(this.getPalindromeId()).withRel("delete_palindrome_" + this.getPalindromeId());
            Link deleteAllLink = linkTo(JaxRsLinkBuilder.class).withRel("delete_palindrome_all");
            this.add(selfLink);
            this.add(getAllLink);
            this.add(deleteLink);
            this.add(deleteAllLink);
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
        builder2.append("CheckPalindromeResponse [palindrome=").append(palindrome).append(", palindromeId=").append(palindromeId).append(", text=").append(text).append("]");
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
    public void setPalindromeId(String id) {
        this.palindromeId = id;
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
