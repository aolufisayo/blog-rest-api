package com.phissy.blog.repository;

import com.phissy.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p WHERE " +
        "p.title LIKE CONCAT('%' , :query , '%')" +
            "OR p.body LIKE CONCAT('%', :query , '%')"
    )
    List<Post> searchPost(String query);
}
