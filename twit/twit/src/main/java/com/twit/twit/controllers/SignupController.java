package com.twit.twit.controllers;

import com.twit.twit.model.ReturnsError;
import com.twit.twit.model.SignupRequest;
import com.twit.twit.model.UserClass;
import com.twit.twit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();

        // Check if the user already exists
        if (userService.findByEmail(email) != null) {
            ReturnsError e = new ReturnsError("Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }

        // Create the new user
        UserClass user = new UserClass();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());

        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }
}
