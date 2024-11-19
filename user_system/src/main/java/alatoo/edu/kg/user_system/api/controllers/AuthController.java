package alatoo.edu.kg.user_system.api.controllers;

import alatoo.edu.kg.user_system.api.payload.user.UserDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginRequestDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginResponseDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserRegisterRequestDTO;
import alatoo.edu.kg.user_system.api.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterRequestDTO dto) {
        log.info("Received registration request: {}", dto);
        try {
            UserDTO userDTO = userService.register(dto);
            log.info("User successfully registered: {}", userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        } catch (Exception e) {
            log.error("Error during registration: {}", e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO dto) {
        log.info("Received login request for login: {}", dto.login());
        try {
            UserLoginResponseDTO response = userService.login(dto);
            log.info("User successfully logged in: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error during login for login {}: {}", dto.login(), e.getMessage(), e);
            throw e;
        }
    }
}
