package alatoo.edu.kg.user_system.api.payload.user;

import alatoo.edu.kg.user_system.store.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {
    private String username;
    private Set<Role> roles;
    private String accessToken;
}