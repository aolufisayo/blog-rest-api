package com.phissy.blog.service.post;

import com.phissy.blog.entity.Post;
import com.phissy.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post newPost) {
        return postRepository.save(newPost);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found with id: "+ postId));
        return post;
    }

    @Override
    public Post updatePost(UUID postId, Post updatedPost) {
        Post oldPost = getPostById(postId);
        oldPost.setTitle(updatedPost != null ? updatedPost.getTitle() : oldPost.getTitle());
        oldPost.setBody(updatedPost != null ? updatedPost.getBody() : oldPost.getBody());
        return postRepository.save(oldPost);
    }

    @Override
    public void deletePost(UUID postId) {
        Post post = getPostById(postId);
        postRepository.delete(post);
    }
}
