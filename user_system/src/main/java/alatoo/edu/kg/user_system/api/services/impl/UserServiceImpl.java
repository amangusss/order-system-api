package alatoo.edu.kg.user_system.api.services.impl;

import alatoo.edu.kg.user_system.api.exceptions.*;
import alatoo.edu.kg.user_system.api.mapper.UserMapper;
import alatoo.edu.kg.user_system.api.payload.user.UserDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginRequestDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginResponseDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserRegisterRequestDTO;
import alatoo.edu.kg.user_system.api.services.JwtService;
import alatoo.edu.kg.user_system.api.services.UserService;
import alatoo.edu.kg.user_system.store.entity.User;
import alatoo.edu.kg.user_system.store.enums.Role;
import alatoo.edu.kg.user_system.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public UserDTO register(UserRegisterRequestDTO dto) {

        if (repository.existsByUsername(dto.username())) {
            throw new UsernameTakenException();
        }

        if (repository.existsByEmail(dto.email())) {
            throw new EmailTakenException();
        }

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new PhoneTakenException();
        }

        User user = userMapper.toUserFromRegisterRequest(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(Role.USER));

        try {
            User savedUser = repository.save(user);
            return userMapper.toDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot register user: %s", e.getMessage()), e);
        }
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO dto) {
        User user = repository.findByUsernameOrEmailOrPhoneNumber(dto.login(), dto.login(), dto.login())
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new AuthException("Incorrect password!");
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        dto.password()
                ));

        String jwtToken = jwtService.generateToken(user);

        return userMapper.toLoginResponseDTOWithToken(user, jwtToken);
    }
}
