package com.dblogApp.services;

import com.dblogApp.dtos.request.UserRegistrationRequest;
import com.dblogApp.dtos.response.UserRegistrationResponse;
import com.dblogApp.exception.RegistrationFailedException;

public interface UserService {

    void deleteAll();

    UserRegistrationResponse createUser(UserRegistrationRequest request) throws RegistrationFailedException;

    boolean existsByEmail(String email);
}
