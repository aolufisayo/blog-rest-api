package com.phissy.blog.service.post;

import com.phissy.blog.entity.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {

    Post addPost(Post newPost);

    List<Post> getAllPosts();

    Post getPostById(UUID postId);

    List<Post> searchPost(String query);

    Post updatePost(UUID postId, Post updatedPost);

    void deletePost(UUID postId);
}
