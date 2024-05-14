package com.dblogApp.data.repositories;

import com.dblogApp.data.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
