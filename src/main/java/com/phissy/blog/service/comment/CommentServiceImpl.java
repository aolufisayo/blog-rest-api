package com.phissy.blog.service.comment;

import com.phissy.blog.entity.Comment;
import com.phissy.blog.entity.Post;
import com.phissy.blog.repository.CommentRepository;
import com.phissy.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment addComment(UUID postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("unable to find post with id: " + postId));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentByPostIdAndId(UUID postId, UUID commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("unable to find post with id: " + postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("unable to find comment with id: "+ commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new RuntimeException("comment does not belong to post with id: "+postId);
        }
        return comment;
    }

    @Override
    public List<Comment> getAllCommentByPostId(UUID postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(UUID postId, UUID commentId, Comment comment) {
        Comment oldComment = getCommentByPostIdAndId(postId, commentId);
        oldComment.setEmail(comment.getEmail() != null ? comment.getEmail() : oldComment.getEmail());
        oldComment.setBody(comment.getBody() != null ? comment.getBody() : oldComment.getBody());
        return commentRepository.save(oldComment);
    }

    @Override
    public void deleteComment(UUID postId, UUID commentId) {
        Comment comment = getCommentByPostIdAndId(postId, commentId);
        commentRepository.delete(comment);
    }
}
