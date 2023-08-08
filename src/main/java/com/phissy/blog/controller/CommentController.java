package com.phissy.blog.controller;

import com.phissy.blog.entity.Comment;
import com.phissy.blog.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("postId") UUID postId ,@Valid @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.addComment(postId,comment), HttpStatus.CREATED);
    }


}
