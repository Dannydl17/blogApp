package com.dblogApp.services;

import com.dblogApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    public void startEachTestWith(){
        userService.deleteAll();
    }

    @Test
    public void testThatUsersTryToRegisterWithIncompleteDetails_RegistrationFailedExceptionIsThrown(){
        assertThatThrownBy(()->userService.createUser(buildUserWithIncompleteDetails()), "Throw For Invalidity")
    }
}
