package com.dblogApp.services;

import com.dblogApp.data.model.Blog;
import com.dblogApp.dtos.request.BlogCreateRequest;

public interface BlogAppService {
    Blog createBlog(BlogCreateRequest blogRequest);
    void deleteAll();


}
