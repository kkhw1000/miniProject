package org.example.miniproject.user;

import lombok.RequiredArgsConstructor;
import org.example.miniproject.exception.DuplicateUserException;
import org.example.miniproject.security.CustomUserDetail;
import org.example.miniproject.user.dto.UserResponseDTO;
import org.example.miniproject.user.dto.UserSignUpRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional(readOnly = false)
    public UserResponseDTO createUser(UserSignUpRequestDTO dto) {

        userRepository.findByLoginId(dto.getLoginId()).orElseThrow(() -> new DuplicateUserException(dto.getLoginId()));

        User from = User.from(dto);
        userRepository.save(from);
        UserResponseDTO responseDTO = UserResponseDTO.from(from);

        return responseDTO;
    }

    @Transactional(readOnly = false)
    public void deleteUser(Long id) {

        userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(id.toString()));

        userRepository.deleteById(id);
    }

}
