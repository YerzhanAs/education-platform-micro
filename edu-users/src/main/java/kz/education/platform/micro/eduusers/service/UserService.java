package kz.education.platform.micro.eduusers.service;


import kz.education.platform.micro.eduentity.entity.ERole;
import kz.education.platform.micro.eduentity.entity.Role;
import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduusers.mapper.UserMapper;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.dto.UserUpdateDTO;
import kz.education.platform.micro.eduusers.models.search.UserSearchValues;
import kz.education.platform.micro.eduusers.repository.RoleRepository;
import kz.education.platform.micro.eduusers.repository.UserRepository;
import kz.education.platform.micro.eduusers.utils.exception.NotFoundException;
import kz.education.platform.micro.eduusers.utils.exception.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    public UserDTO getUserById(Long userId) {
        User user =userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        return userMapper.convertToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new NotFoundException("No users found");
        }

        return users.stream().map(userMapper::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateDTO userUpdateDTO) {

        User existingUser = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        checkValid(userUpdateDTO.getUsername(), userUpdateDTO.getEmail());

        User updatedU = userMapper.convertToUser(userUpdateDTO);

        updatedU.setId(existingUser.getId());
        updatedU.setRoles(assignRoles(userUpdateDTO.getRoles()));

        userRepository.save(updatedU);
    }


    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Transactional
    public void saveUser(UserUpdateDTO userUpdateDTO) {

        checkValid(userUpdateDTO.getUsername(), userUpdateDTO.getEmail());

        User user = userMapper.convertToUser(userUpdateDTO);

        user.setRoles(assignRoles(userUpdateDTO.getRoles()));

        userRepository.save(user);
    }

    public Page<UserDTO> findUserByParams(UserSearchValues userSearchValues){
         String email = userSearchValues.getEmail() != null ? userSearchValues.getEmail() : null;

         String username = userSearchValues.getUsername() != null ? userSearchValues.getUsername() : null;

         String sortColumn = userSearchValues.getSortColumn() !=null ? userSearchValues.getSortColumn() : null;
         String sortDirection = userSearchValues.getSortDirection() !=null ? userSearchValues.getSortDirection() : null;

        Integer pageNumber = userSearchValues.getPageNumber() !=null ? userSearchValues.getPageNumber() : null;
        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn, "id");

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> result = userRepository.findByParams(email, username, pageRequest);

        Page<UserDTO> resultDTO = userMapper.convertToUserDTOPage(result);

        return resultDTO;
    }



    private Set<Role> assignRoles(Set<String> reqRoles) {
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null || reqRoles.isEmpty()) {
            roles.add(findRoleByName(ERole.ROLE_USER));
        } else {
            reqRoles.forEach(roleName -> {
                switch (roleName){
                    case "admin":
                        Role roleAdmin = findRoleByName(ERole.ROLE_ADMIN);
                        roles.add(roleAdmin);
                        break;
                    case "mod":
                        Role roleMod = findRoleByName(ERole.ROLE_MODERATOR);
                        roles.add(roleMod);
                        break;
                    default:
                        Role roleUser = findRoleByName(ERole.ROLE_USER);
                        roles.add(roleUser);
                }
            });
        }

        return roles;
    }

    private Role findRoleByName(ERole roleName) {
        return roleRepository
                .findByName(roleName)
                .orElseThrow(() -> new NotFoundException("Error, Role " + roleName + " is not found"));
    }


    private void checkValid(String username, String email) {

        if (userRepository.existsByUsername(username)) {
            throw new UserRegistrationException("Username '" + username + "' is already taken.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email '" + email + "' is already registered.");
        }

    }


}
