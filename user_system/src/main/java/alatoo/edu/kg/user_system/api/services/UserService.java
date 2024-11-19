package alatoo.edu.kg.user_system.api.services;

import alatoo.edu.kg.user_system.api.payload.user.UserDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginRequestDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginResponseDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserRegisterRequestDTO;

public interface UserService {
    UserDTO register(UserRegisterRequestDTO dto);
    UserLoginResponseDTO login(UserLoginRequestDTO dto);
}
