package com.dblogApp.data.repositories;

import com.dblogApp.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findUserByEmail(String email);
}
