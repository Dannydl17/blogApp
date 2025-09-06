package com.dblogApp.services;

import com.dblogApp.data.model.Blog;
import com.dblogApp.dtos.request.BlogCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BlogAppServiceTest {
    @Autowired
    private BlogAppService blogAppService;

    @BeforeEach
    public void startEachTestWith(){
        blogAppService.deleteAll();
    }

    @Test
    public void testThatBlogCanBeCreatedTest(){
        BlogCreateRequest blogRequest = new BlogCreateRequest();
        blogRequest.setTitle("Getting Started With Spring Boot");
        blogRequest.setContent("Spring Boot makes it easy to create stand-alone...");
        Blog blog = blogAppService.createBlog(blogRequest);
        assertEquals("Spring Boot makes it easy to create stand-alone...", blog.getContent());
    }
}
