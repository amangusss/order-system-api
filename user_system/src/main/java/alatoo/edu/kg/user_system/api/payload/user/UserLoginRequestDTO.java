package alatoo.edu.kg.user_system.api.payload.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDTO(
        @NotBlank(message = "Login is empty")
        String login,

        @NotBlank(message = "Password is empty")
        String password
) {}
