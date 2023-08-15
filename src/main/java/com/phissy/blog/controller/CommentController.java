package com.phissy.blog.controller;

import com.phissy.blog.entity.Comment;
import com.phissy.blog.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable("postId") UUID postId){
        return new ResponseEntity<>(commentService.getAllCommentByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByPostIdAndCommentId(@PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId){
        return new ResponseEntity<>(commentService.getCommentByPostIdAndId(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId,@RequestBody Comment newComment){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, newComment), HttpStatus.OK);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId){
        commentService.deleteComment(postId, commentId);
    }



}
