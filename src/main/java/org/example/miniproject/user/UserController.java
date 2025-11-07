package org.example.miniproject.user;

import lombok.RequiredArgsConstructor;
import org.example.miniproject.security.CustomUserDetail;
import org.example.miniproject.user.dto.UserResponseDTO;
import org.example.miniproject.user.dto.UserSignUpRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<String> signup(@RequestBody UserSignUpRequestDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("회원 삭제 성공");
    }
}
