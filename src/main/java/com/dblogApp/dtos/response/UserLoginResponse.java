package com.dblogApp.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse {
    private String email;
    private String password;
}
