package org.example.miniproject.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniproject.user.dto.UserSignUpRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String loginId;

    @Column(length = 100)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;


    public static User from(UserSignUpRequestDTO dto) {
        return User.builder()
                .username(dto.getName())
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }


}
