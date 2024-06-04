package com.donggyu.dginside.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank(message = "The email field is required")
    @Email
    private String email;

    @NotBlank(message = "The nickname field is required")
    private String nickname;
}
