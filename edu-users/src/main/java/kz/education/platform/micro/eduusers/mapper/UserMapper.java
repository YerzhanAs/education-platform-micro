package kz.education.platform.micro.eduusers.mapper;

import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserUpdateDTO convertToUpdateDTO(User user) {
        return modelMapper.map(user, UserUpdateDTO.class);
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public User convertToUser(UserUpdateDTO userCreateDTO) {
        return modelMapper.map(userCreateDTO, User.class);
    }

    public Page<UserDTO> convertToUserDTOPage(Page<User> userPage) {
        return userPage.map(this::convertToUserDTO);
    }
}
