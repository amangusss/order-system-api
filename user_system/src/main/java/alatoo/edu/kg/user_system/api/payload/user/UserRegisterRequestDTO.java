package alatoo.edu.kg.user_system.api.payload.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO(
        @NotBlank(message = "Username is empty")
        String username,

        @NotBlank(message = "Password is empty")
        String password,

        @NotBlank(message = "Email is empty")
        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Full name is empty")
        String fullName,

        @NotBlank(message = "Phone number is empty")
        String phoneNumber
) {}
