package com.keycloak.zig.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keycloak.zig.dto.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> login(UserCredential userCredential) throws JsonProcessingException {
        // Define the Keycloak token endpoint URL
        String keycloakTokenUrl = "http://localhost:8080/realms/test-zig/protocol/openid-connect/token";

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set up the request body parameters
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("grant_type", "password");
        bodyParams.add("client_id", "xpns-test");
        bodyParams.add("username", userCredential.getUserName());
        bodyParams.add("password", userCredential.getPassword());

        // Create the request entity with headers and body
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyParams, headers);

        System.out.println("Req Body :: "+ requestEntity);
        // Send the POST request to the Keycloak token endpoint
        ResponseEntity<String> response = restTemplate.exchange(keycloakTokenUrl, HttpMethod.POST, requestEntity, String.class);

        // Return the token response
        return response;
    }


//    public ResponseEntity<?> authenticateUser(SAMLCredential samlCredential) {
//        return null;
//    }
}
