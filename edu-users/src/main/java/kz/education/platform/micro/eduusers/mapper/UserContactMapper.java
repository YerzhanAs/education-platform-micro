package kz.education.platform.micro.eduusers.mapper;

import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduentity.entity.UserContact;
import kz.education.platform.micro.eduusers.models.dto.UserContactDTO;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContactMapper {

    private final ModelMapper modelMapper;


    public UserContactDTO convertToUserContactDTO(UserContact userContact) {
        return modelMapper.map(userContact, UserContactDTO.class);
    }

    public UserContact convertToUserContact(UserContactDTO userContactDTO){
        return modelMapper.map(userContactDTO, UserContact.class);
    }

    public Page<UserContactDTO> convertToUserContactDTOPage(Page<UserContact> userContactPage) {
        return userContactPage.map(this::convertToUserContactDTO);
    }
}
