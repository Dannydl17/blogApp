package com.dblogApp.services;

import com.dblogApp.data.model.User;
import com.dblogApp.data.repositories.UserRepository;
import com.dblogApp.dtos.request.UserLoginRequest;
import com.dblogApp.dtos.request.UserRegistrationRequest;
import com.dblogApp.dtos.response.UserRegistrationResponse;
import com.dblogApp.exception.InvalidDetailsException;
import com.dblogApp.exception.RegistrationFailedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.dblogApp.utils.Constants.USER_REGISTRATION_SUCCESSFUL_MESSAGE;

@Service
@AllArgsConstructor
public class BlogUserService implements UserService{
   private final UserRepository userRepository;
   public Validator validator;

   @Override
   public void deleteAll() {
      userRepository.deleteAll();
   }

   @Override
   public UserRegistrationResponse createUser(UserRegistrationRequest request) throws RegistrationFailedException {
      try{
         Set<ConstraintViolation<UserRegistrationRequest>> violations = validator.validate(request);
         if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
         }
         User user = new User();
         user.setUserName(request.getName());
         user.setEmail(request.getEmail());
         user.setPassword(request.getPassword());

         User savedUser = userRepository.save(user);
         UserRegistrationResponse response = new UserRegistrationResponse();
         response.setName(savedUser.getUserName());
         response.setEmail(savedUser.getEmail());
         response.setMessage(USER_REGISTRATION_SUCCESSFUL_MESSAGE);
         return response;
      }catch (Exception exception) {
         throw new RegistrationFailedException(exception.getMessage(), exception);
      }
   }

   @Override
   public boolean existsByEmail(String email) {
      return userRepository.existsByEmail(email);
   }

   @Override
   public void login(UserLoginRequest request) {
      User user = validateUserLogin(request.getEmail(), request.getPassword());
   }

   private User validateUserLogin(String email, String password) {
      User user = userRepository.findUserByEmail(email);
      if (user != null && user.getPassword().equals(password)){
         return user;
      }
      throw new InvalidDetailsException();
   }
}
