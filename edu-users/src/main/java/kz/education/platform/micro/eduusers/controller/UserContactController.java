package kz.education.platform.micro.eduusers.controller;


import kz.education.platform.micro.eduusers.models.dto.UserContactDTO;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.dto.UserUpdateDTO;
import kz.education.platform.micro.eduusers.models.search.UserContactSearchValues;
import kz.education.platform.micro.eduusers.models.search.UserSearchValues;
import kz.education.platform.micro.eduusers.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user-contact")
@RequiredArgsConstructor
public class UserContactController {

    private final UserContactService userContactService;

    @PostMapping("/search")
    public ResponseEntity<Page<UserContactDTO>> search(@RequestBody UserContactSearchValues userContactSearchValues){
        Page<UserContactDTO> result = userContactService.findUserByParams( userContactSearchValues);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserContactDTO>> getAllUsers(){
        return ResponseEntity.ok(userContactService.getAllUsersContact());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserContactDTO> getUserById(@PathVariable("id") Long id) {
        return  ResponseEntity.ok().body(userContactService.getUserContactById(id));
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<UserContactDTO> getUserByUserId(@PathVariable("userId") Long id) {
        return  ResponseEntity.ok().body(userContactService.getUserContactByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserContact(@PathVariable("id") Long id){
        userContactService.deleteUserContact(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody UserContactDTO userContactDTO){
        userContactService.saveUserContact(userContactDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update (@Valid @RequestBody UserContactDTO userContactDTO,
                                              @PathVariable("id") Long id){

        userContactService.updateUserContact(id,userContactDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }




}
