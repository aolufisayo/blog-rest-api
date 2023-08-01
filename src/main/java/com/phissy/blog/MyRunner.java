package com.phissy.blog;

import com.phissy.blog.entity.Post;
import com.phissy.blog.repository.PostRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BlogApplication.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("------------------------");
        logger.info("Blog Application started....");
        logger.info("# of posts: {}", postRepository.count());
        logger.info("------------------------");
    }
}
