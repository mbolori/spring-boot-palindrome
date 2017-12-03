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
 *
 */
@Component
@Path("/palindromes")
public class PalindromeResource {
 
    private final Logger log = LoggerFactory.getLogger(PalindromeResource.class);
    
    @Context
    protected HttpServletRequest req;
    
    @Autowired
    private PalindromeService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkIfPalindrome(String json) throws JsonException, PalindromeServiceException {
        log.info("checkIfPalindrome endpoint. Payload: "  + json);   
        CheckPalindromeResponse response = service.checkIfPalindrome(CheckPalindromeRequest.fromJson(json));     
        return Response.ok(response.toJson()).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPalindrome() throws JsonException {
        log.info("Get all Palindrome endpoint.");
        Collection<Palindrome> palindromeList = service.getAllPalindrome();
        return Response.ok(Palindrome.toJsonCollection(palindromeList)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPalindromeById(@PathParam("id") String id) throws PalindromeServiceException, JsonException {
        log.info("Get Palindrome endpoint with id: " + id);
        Palindrome palindrome = service.getPalindromeById(id);
        return Response.ok(palindrome.toJson()).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAllPalindrome() {
        log.info("Delete all Palindrome endpoint.");
        service.removeAllPalindrome();
        return Response.ok().build();
    }
    
    
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
