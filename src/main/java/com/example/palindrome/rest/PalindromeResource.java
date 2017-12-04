package com.example.palindrome.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.palindrome.domain.CheckPalindromeRequest;
import com.example.palindrome.domain.CheckPalindromeResponse;
import com.example.palindrome.domain.Palindrome;
import com.example.palindrome.service.JsonException;
import com.example.palindrome.service.PalindromeServiceException;
import com.example.palindrome.service.api.PalindromeService;

/**
 * JAX-RS Resource class. It presents several endpoints mapped to URL: /spring-boot-palindrome/api/palindromes
 */
@Component
@Path("/palindromes")
public class PalindromeResource {
 
    private final Logger log = LoggerFactory.getLogger(PalindromeResource.class);
    
    /** The req. */
    @Context
    protected HttpServletRequest req;
    
    @Autowired
    private PalindromeService service;
    
    /**
     * Check if text is a palindrome.
     *
     * @param json the json
     * @return the response
     * @throws JsonException the json exception
     * @throws PalindromeServiceException the palindrome service exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkIfPalindrome(String json) throws JsonException, PalindromeServiceException {
        log.info("checkIfPalindrome endpoint. Payload: "  + json);   
        CheckPalindromeResponse response = service.checkIfPalindrome(CheckPalindromeRequest.fromJson(json));     
        return Response.ok(response.toJson()).build();
    }
    
    
    /**
     * Gets the all palindrome.
     *
     * @return the all palindrome
     * @throws JsonException the json exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPalindrome() throws JsonException {
        log.info("Get all Palindrome endpoint.");
        Collection<Palindrome> palindromeList = service.getAllPalindrome();
        return Response.ok(Palindrome.toJsonCollection(palindromeList)).build();
    }
    
    /**
     * Gets the palindrome by id.
     *
     * @param id the id
     * @return the palindrome by id
     * @throws PalindromeServiceException the palindrome service exception
     * @throws JsonException the json exception
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPalindromeById(@PathParam("id") String id) throws PalindromeServiceException, JsonException {
        log.info("Get Palindrome endpoint with id: " + id);
        Palindrome palindrome = service.getPalindromeById(id);
        return Response.ok(palindrome.toJson()).build();
    }
    
    /**
     * Delete all palindrome.
     *
     * @return the response
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAllPalindrome() {
        log.info("Delete all Palindrome endpoint.");
        service.removeAllPalindrome();
        return Response.ok().build();
    }
    
    
    /**
     * Delete palindrome by id.
     *
     * @param id the id
     * @return the response
     * @throws PalindromeServiceException the palindrome service exception
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePalindromeById(@PathParam("id") String id) throws PalindromeServiceException {
        log.info("Delete Palindrome endpoint with id: " + id);
        service.removePalindromeById(id);
        return Response.ok().build();
    }
}
