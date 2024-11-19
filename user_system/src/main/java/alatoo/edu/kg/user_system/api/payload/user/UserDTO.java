package alatoo.edu.kg.user_system.api.payload.user;

import alatoo.edu.kg.user_system.store.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private Set<Role> roles;
}
