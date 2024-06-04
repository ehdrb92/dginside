package com.donggyu.dginside.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinDTO {

    /*
    * @NotBlank: 해당 필드가 비어있으면 안되도록 설정
    * @Size: 해당 필드의 최소, 최대값을 설정
    * */
    @NotBlank(message = "The username field is required")
    @Size(max = 20, message = "Up to 20 characters")
    private String username;

    @NotBlank(message = "The password field is required")
    private String password;

    @NotBlank(message = "The name field is required")
    @Size(max = 10, message = "Up to 10 characters")
    private String name;

    @NotBlank(message = "The email field is required")
    @Email
    private String email;

    @NotBlank(message = "The nickname field is required")
    private String nickname;
}
