package com.phissy.blog.controller;

import com.phissy.blog.dto.UserModel;
import com.phissy.blog.entity.User;
import com.phissy.blog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> readUser(@RequestParam("email") String email){
        return new ResponseEntity<>(userService.readUser(email), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam("email") String email, @RequestBody UserModel userModel){
        return new ResponseEntity<>(userService.updateUser(email, userModel), HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam("email") String email){
        userService.deleteUser(email);
    }
}
