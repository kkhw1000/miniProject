package org.example.miniproject.user.dto;

import lombok.Getter;

@Getter
public class UserSignUpRequestDTO {
    private String name;
    private String loginId;
    private String password;
    private String email;

}
