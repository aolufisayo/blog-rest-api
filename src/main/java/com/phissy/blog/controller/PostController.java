package com.phissy.blog.controller;

import com.phissy.blog.entity.Post;
import com.phissy.blog.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") UUID postId){
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @ResponseStatus(value=HttpStatus.OK)
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") UUID postId,@RequestBody Post post){
        return new ResponseEntity<>(postService.updatePost(postId, post), HttpStatus.OK);
    }

    @ResponseStatus(value=HttpStatus.NO_CONTENT )
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") UUID postId){
        postService.deletePost(postId);
    }

}
