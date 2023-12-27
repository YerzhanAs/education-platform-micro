package kz.education.platform.micro.eduusers.service;


import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduentity.entity.UserContact;
import kz.education.platform.micro.eduusers.mapper.UserContactMapper;
import kz.education.platform.micro.eduusers.models.dto.UserContactDTO;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.search.UserContactSearchValues;
import kz.education.platform.micro.eduusers.models.search.UserSearchValues;
import kz.education.platform.micro.eduusers.repository.UserContactRepository;
import kz.education.platform.micro.eduusers.repository.UserRepository;
import kz.education.platform.micro.eduusers.utils.exception.NotFoundException;
import kz.education.platform.micro.eduusers.utils.exception.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.NotActiveException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserContactService {

    private final UserContactRepository userContactRepository;

    private final UserRepository userRepository;

    private final UserContactMapper userContactMapper;

    public List<UserContactDTO> getAllUsersContact(){
        List<UserContact> usersContact = userContactRepository.findAll();

        if(usersContact.isEmpty()){
            throw new NotFoundException("No users contact found");
        }

        return usersContact.stream().map(userContactMapper::convertToUserContactDTO)
                .collect(Collectors.toList());
    }

    public UserContactDTO getUserContactById(Long userContactId){
        UserContact userContact = userContactRepository.findById(userContactId)
                .orElseThrow(() -> new NotFoundException("User contact not found with ID: " + userContactId));

        return userContactMapper.convertToUserContactDTO(userContact);
    }

    public UserContactDTO getUserContactByUserId(Long userId){
        User user = new User();
        user.setId(userId);
        return userContactMapper.convertToUserContactDTO(userContactRepository.findUserContactByUser(user));
    }

    @Transactional
    public void deleteUserContact(Long id){
        userContactRepository.deleteById(id);
    }

    @Transactional
    public void saveUserContact(UserContactDTO userContactDTO){
//        checkValid(userContactDTO.getUser().getId());

        UserContact userContact = userContactMapper.convertToUserContact(userContactDTO);

        userContactRepository.save(userContact);
    }

    @Transactional
    public void updateUserContact(Long id, UserContactDTO userContactDTO){
        UserContact existingUserContact =userContactRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User contact not found with ID: " + id));

//        checkValid(userContactDTO.getUser().getId());

        UserContact updateContact = userContactMapper.convertToUserContact(userContactDTO);

        updateContact.setId(existingUserContact.getId());


        userContactRepository.save(updateContact);
    }


    public Page<UserContactDTO> findUserByParams(UserContactSearchValues userContactSearchValues){

        String address = userContactSearchValues.getAddress() != null ? userContactSearchValues.getAddress() : null;
        String mobileNumber = userContactSearchValues.getMobileNumber()!= null ? userContactSearchValues.getMobileNumber() : null;
        String city = userContactSearchValues.getCity()!= null ? userContactSearchValues.getCity() : null;
        String region = userContactSearchValues.getRegion()!= null ? userContactSearchValues.getRegion(): null;

        //Sort
        String sortColumn = userContactSearchValues.getSortColumn() !=null ? userContactSearchValues.getSortColumn() : null;
        String sortDirection = userContactSearchValues.getSortDirection() !=null ? userContactSearchValues.getSortDirection() : null;

        //Pageable
        Integer pageNumber = userContactSearchValues.getPageNumber() !=null ? userContactSearchValues.getPageNumber() : null;
        Integer pageSize = userContactSearchValues.getPageSize() != null ? userContactSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn, "id");

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<UserContact> result = userContactRepository.findByParams(address, mobileNumber, city, region, pageRequest);

        Page<UserContactDTO> resultDTO = userContactMapper.convertToUserContactDTOPage(result);

        return resultDTO;
    }

    private void checkValid(Long id){
        if(userRepository.existsById(id)){
            throw new UserRegistrationException("User id '" + id + "' is already taken.");
        }
    }

}
