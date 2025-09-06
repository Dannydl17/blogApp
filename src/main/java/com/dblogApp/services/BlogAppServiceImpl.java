package com.dblogApp.services;

import com.dblogApp.data.model.Blog;
import com.dblogApp.data.model.User;
import com.dblogApp.data.repositories.BlogRepository;
import com.dblogApp.data.repositories.UserRepository;
import com.dblogApp.dtos.request.BlogCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogAppServiceImpl implements BlogAppService {
    private final BlogRepository blogRepository;

    @Override
    public Blog createBlog(BlogCreateRequest blogRequest) {
        Blog blog = new Blog();
        blog.setTitle(blog.getTitle());
        blog.setContent(blog.getContent());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }

}
