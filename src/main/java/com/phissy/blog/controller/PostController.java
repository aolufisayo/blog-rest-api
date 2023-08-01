package com.phissy.blog.controller;

import com.phissy.blog.entity.Post;
import com.phissy.blog.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED);
    }

}
