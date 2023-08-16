package com.phissy.blog.controller;

import com.phissy.blog.dto.AuthModel;
import com.phissy.blog.dto.UserModel;
import com.phissy.blog.entity.User;
import com.phissy.blog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthModel authModel){
        return "login";
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserModel userModel){
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}
