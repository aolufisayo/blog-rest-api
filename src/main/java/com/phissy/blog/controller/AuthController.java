package com.phissy.blog.controller;

import com.phissy.blog.dto.AuthModel;
import com.phissy.blog.dto.JwtToken;
import com.phissy.blog.dto.UserModel;
import com.phissy.blog.entity.User;
import com.phissy.blog.service.user.CustomUserDetailService;
import com.phissy.blog.service.user.UserService;
import com.phissy.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@Valid @RequestBody AuthModel authModel)throws Exception{
        authenticate(authModel.getEmail(), authModel.getPassword());
        final UserDetails userDetails = customUserDetailService.loadUserByUsername(authModel.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(new JwtToken(token), HttpStatus.OK);
    }

    private void authenticate(String email, String password)throws Exception {
        try {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("User disabled!");
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserModel userModel){
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}
