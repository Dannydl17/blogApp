package com.dblogApp.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.dblogApp.utils.Constants.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    @NotBlank(message = BLANK_FIELD_MESSAGE)
    private String name;
    @NotBlank(message =  BLANK_FIELD_MESSAGE)
    @Email(regexp = EMAIL_PATTERN, message = INVALID_EMAIL_MESSAGE)
    private String email;
    @NotBlank(message = BLANK_FIELD_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = INVALID_PASSWORD_MESSAGE)
    private String password;
}
