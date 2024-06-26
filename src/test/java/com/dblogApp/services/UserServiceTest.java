package com.dblogApp.services;

import com.dblogApp.data.repositories.UserRepository;
import com.dblogApp.dtos.request.UserLoginRequest;
import com.dblogApp.dtos.request.UserRegistrationRequest;
import com.dblogApp.dtos.response.UserRegistrationResponse;
import com.dblogApp.exception.RegistrationFailedException;
import com.dblogApp.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dblogApp.utils.Constants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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

    @Test
    @SneakyThrows
    public void testThatUsersCanRegisterSuccessfully_IfAllChecksArePassed(){
        UserRegistrationResponse response = userService.createUser(buildValidUser());
        assertThat(userService.existsByEmail(response.getEmail())).isTrue();
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getMessage()).isEqualTo(USER_REGISTRATION_SUCCESSFUL_MESSAGE);
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

    private UserRegistrationRequest buildValidUser() {
        return UserRegistrationRequest.builder()
                .password("Dan01@Big_Dawg")
                .email("email@native.semicolon.africa")
                .name("Danny_Big_Dawg")
                .build();
    }

    @Test
    public void testThatUserCanLoginTest() throws RegistrationFailedException {
        UserRegistrationResponse response = userService.createUser(buildValidUser());
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail(response.getEmail());
        request.setPassword("Dan01@Big_Dawg");
        userService.login(request);
        assertEquals(1, userRepository.count());
    }
}
