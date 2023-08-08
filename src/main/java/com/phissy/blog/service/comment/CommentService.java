package com.phissy.blog.service.comment;

import com.phissy.blog.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    Comment addComment(UUID postId, Comment comment);

    Comment getCommentByPostIdAndId(UUID postId, UUID commentId);

    List<Comment> getAllCommentByPostId(UUID postId);

    Comment updateComment(UUID postId , UUID commentId, Comment comment);

    void deleteComment(UUID postId, UUID commentId);
}
