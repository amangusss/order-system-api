package alatoo.edu.kg.user_system.api.mapper;

import alatoo.edu.kg.user_system.api.payload.user.UserDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserLoginResponseDTO;
import alatoo.edu.kg.user_system.api.payload.user.UserRegisterRequestDTO;
import alatoo.edu.kg.user_system.store.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUserFromRegisterRequest(UserRegisterRequestDTO dto);

    @Mapping(target = "accessToken", ignore = true)
    UserLoginResponseDTO toLoginResponseDTO(User user);

    default UserLoginResponseDTO toLoginResponseDTOWithToken(User user, String accessToken) {
        UserLoginResponseDTO dto = toLoginResponseDTO(user);
        dto.setAccessToken(accessToken);
        return dto;
    }
}

