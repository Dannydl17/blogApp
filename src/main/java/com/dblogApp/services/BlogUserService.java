package com.dblogApp.services;

import com.dblogApp.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogUserService implements UserService{
   private final UserRepository userRepository;

   @Override
   public void deleteAll() {
      userRepository.deleteAll();
   }
}
