package com.dblogApp.dtos.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponse {
    private String name;
    private String email;
    private String message;
}
