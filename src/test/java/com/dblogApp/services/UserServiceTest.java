package com.dblogApp.services;

import com.dblogApp.dtos.request.UserRegistrationRequest;
import com.dblogApp.exception.RegistrationFailedException;
import com.dblogApp.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dblogApp.utils.Constants.*;
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
        assertThatThrownBy(()-> userService.createUser(buildUserWithIncompleteDetails()), "Thrown For Invalidity")
                .isInstanceOf(RegistrationFailedException.class)
                .hasMessageContaining(BLANK_FIELD_MESSAGE)
                .hasMessage("password: " + BLANK_FIELD_MESSAGE)
                .cause();

    }

    @Test
    @SneakyThrows
    public void testThatUsersTryToRegisterWithInvalidEmailFormat_RegistrationFailedExceptionIsThrown(){
        assertThatThrownBy(()->userService.createUser(buildUserWithInvalidEmail()), "Throw For Invalidity")
                .isInstanceOf(RegistrationFailedException.class)
                .hasMessageContaining(INVALID_EMAIL_MESSAGE)
//                .hasMessage("email" + INVALID_EMAIL_MESSAGE)
                .cause();
    }


    @Test
    @SneakyThrows
    public void testThatUsersTryToRegisterWithInvalidPasswordFormat_RegistrationFailedExceptionIsThrown(){
        assertThatThrownBy(()->userService.createUser(buildUserWithInvalidPassword()))
                .hasMessageContaining(INVALID_PASSWORD_MESSAGE)
//                .hasMessage("password" + INVALID_PASSWORD_MESSAGE)
                .isInstanceOf(RegistrationFailedException.class)
                .cause();
    }

    private UserRegistrationRequest buildUserWithInvalidEmail() {
        return UserRegistrationRequest.builder()
                .password("Hello World")
                .email("email")
                .name("Danny_Big_Dawg")
                .build();
    }

    private UserRegistrationRequest buildUserWithIncompleteDetails() {
        return UserRegistrationRequest.builder()
                .name("Danny")
                .email("test@email.com")
                .build();
    }

    private UserRegistrationRequest buildUserWithInvalidPassword() {
        return UserRegistrationRequest.builder()
                .name("Danny_Big_Dawg")
                .email("email@gmail.com")
                .password("Hello World")
                .build();
    }
}
