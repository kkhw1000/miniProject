package org.example.miniproject.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.miniproject.user.User;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDTO {

    private Long id;
    private String username;
    private String loginId;
    private String email;


    public static UserResponseDTO from(User from) {
        return UserResponseDTO.builder()
                .id(from.getId())
                .username(from.getUsername())
                .loginId(from.getLoginId())
                .email(from.getEmail())
                .build();

    }
}
