package com.example.palindrome;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.MDC;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.palindrome.rest.PalindromeResource;
import com.example.palindrome.service.JsonException;
import com.example.palindrome.service.PalindromeServiceException;
import com.example.palindrome.service.PalindromeServiceException.Causes;

/**
 * Extend Jersey JAX-RS ResourceConfig class to define:
 *   Resource classes
 *   Use of Jackson Json provider
 *   Exception mappers
 *   Filters.
 */
@Component
@ApplicationPath(value="/spring-boot-palindrome/api")
public class JerseyConfiguration extends ResourceConfig {

    private final static Logger log = LoggerFactory.getLogger(JerseyConfiguration.class);
    
    /**
     * Instantiates a new jersey configuration.
     */
    public JerseyConfiguration() {
        this.register(JacksonFeature.class);
        
        this.register(PalindromeResource.class);
        
        this.register(PalindromeServiceExceptionMapper.class);
        
        this.register(JsonExceptionMapper.class);
        
        this.register(RequestIdFilter.class);
        
        this.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
    }

    /**
     * ExceptionMapper for PalindromeServiceException.
     * It takes care of translating PalindromeServiceException into proper JAX-RS Response
     * It follows following rules:
     *    if PalindromeServiceException's cause is NoText: If request has text field empty: Returns 400 (BAD REQUEST)
     *    if PalindromeServiceException's cause is PalindromeIdExpected: If palindromeId is missing: Returns 400 (BAD REQUEST)
     *    if PalindromeServiceException's cause is PalindromeNotFound: If there is no palindrome by palindromeId: Returns 404 (NOT FOUND)
     *    Else Returns 500 (INTERNAL SERVER ERROR).
     */
    public static class PalindromeServiceExceptionMapper implements ExceptionMapper<PalindromeServiceException> {

        /**
         * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
         *
         * @param exception
         * @return
         */
        @Override
        public Response toResponse(PalindromeServiceException exception) {
            if (exception.getExceptionCause() == Causes.NoText) {
                log.info("Translating exception: " + exception + " into HTTP Status code 400. ");
                return Response.status(Status.BAD_REQUEST).build();
                
            } else if (exception.getExceptionCause() == Causes.PalindromeIdExpected) {
                log.info("Translating exception: " + exception + " into HTTP Status code 400. ");
                return Response.status(Status.BAD_REQUEST).build();
                
            } else if (exception.getExceptionCause() == Causes.PalindromeNotFound) {
                log.info("Translating exception: " + exception + " into HTTP Status code 404. ");
                return Response.status(Status.NOT_FOUND).build();
                
            }
            
            log.info("Translating exception: " + exception + " into HTTP Status code 500. ");
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Server Error. Msg: " + exception.getMessage()).build();
        }
        
    }
    
    /**
     * ExceptionMapper for JsonException.
     * It takes care of translating JsonException into proper JAX-RS Response: 400 (BAD REQUEST).
     *
     */
    public static class JsonExceptionMapper implements ExceptionMapper<JsonException> {

        /**
         * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
         *
         * @param exception
         * @return
         */
        @Override
        public Response toResponse(JsonException exception) {
            log.info("Translated exception: " + exception + " into HTTP Status Code 400.");
            return Response.status(Status.BAD_REQUEST).build();
        }
        
    }
    
    /**
     * Request filter to inject RequestId on context, to be used by slf4j.
     */
    public static class RequestIdFilter implements ContainerRequestFilter {

        /** The Constant RequestId. */
        public static final String RequestId = "RequestId";
        
        private AtomicLong counter = new AtomicLong(1);
        
        /**
         * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
         *
         * @param requestContext
         * @throws IOException
         */
        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {
            MDC.put(RequestId, counter.getAndIncrement()); 
        }
        
    }
}
