package com.dblogApp.services;

import com.dblogApp.dtos.request.UserLoginRequest;
import com.dblogApp.dtos.request.UserRegistrationRequest;
import com.dblogApp.dtos.response.UserRegistrationResponse;
import com.dblogApp.exception.RegistrationFailedException;

public interface UserService {
    UserRegistrationResponse createUser(UserRegistrationRequest request) throws RegistrationFailedException;
    void deleteAll();

    boolean existsByEmail(String email);

    void login(UserLoginRequest request);
}
