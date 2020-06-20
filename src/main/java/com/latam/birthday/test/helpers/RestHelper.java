package com.latam.birthday.test.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 *
 * This contains request rest helper
 * @author Jorge Silva Aguilera
 */
public class RestHelper {

    /**
     *
     * Instantiates a new request helper.
     */
    private RestHelper() {
        super();
    }


    /**
     * Creates the header.
     *
     * @param token the token
     * @return the HTTP entity
     */
    public static final HttpEntity<String> createHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }


    /**
     * Creates the header and JSON.
     *
     * @param token the token
     * @param rq the RQ
     * @return the HTTP entity
     */
    public static final HttpEntity<String> createHeaderAndJson(String token, String rq) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(rq, headers);
    }


    /**
     * Rest template.
     *
     * @return the rest template
     */
    private static RestTemplate createRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        return new RestTemplate(factory);
    }


    /**
     *          Call to service
     *
     * @param uri the builder
     * @param httpMethod the http method
     * @param entity the entity
     * @return the object
     * @throws Exception generic exception
     */
    public static final String callService(String uri, HttpMethod httpMethod, HttpEntity<String> entity) throws Exception {
        try {
            RestTemplate restTemplate = createRestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(uri, httpMethod, entity,
                    new ParameterizedTypeReference<String>(){});
            return response.getBody();
        } catch(ResourceAccessException rex) {
            throw new ResourceAccessException(rex.getMessage());
        } catch (HttpClientErrorException hex) {
            if(HttpStatus.UNAUTHORIZED.equals(hex.getStatusCode()))
                throw new Exception(hex);
            if(HttpStatus.UNPROCESSABLE_ENTITY.equals(hex.getStatusCode()))
                throw new Exception(hex);
            if(HttpStatus.INTERNAL_SERVER_ERROR.equals(hex.getStatusCode()))
                throw new Exception(hex);
            if(HttpStatus.CONFLICT.equals(hex.getStatusCode()))
                throw new Exception(hex);
            throw new Exception(hex);
        }
    }


}
