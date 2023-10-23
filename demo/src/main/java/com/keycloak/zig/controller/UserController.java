package com.keycloak.zig.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keycloak.zig.dto.UserCredential;
import com.keycloak.zig.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/access")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
//    @PreAuthorize("hasRole('uma_authorization')")
    public ResponseEntity<?> loginUser(@RequestBody UserCredential userCredential) throws JsonProcessingException {
        ResponseEntity<?> responseEntity = userService.login(userCredential);
        return responseEntity;
    }

//    @PostMapping("/saml/login")
//    public ResponseEntity<?> loginSAMLUser(@RequestBody SAMLCredential samlCredential) {
//        // Validate the SAML credential and authenticate the user
//        ResponseEntity<?> responseEntity = userService.authenticateUser(samlCredential);
//        return responseEntity;
//    }

    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @RolesAllowed("user")
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser(@RequestHeader String Authorization) {
        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin(@RequestHeader String Authorization) {
        return ResponseEntity.ok("Hello Admin");
    }

    @RolesAllowed({ "admin", "user" })
    @RequestMapping(value = "/all-user", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser(@RequestHeader String Authorization) {
        return ResponseEntity.ok("Hello All User");
    }
}