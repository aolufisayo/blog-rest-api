package com.phissy.blog.repository;

import com.phissy.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
