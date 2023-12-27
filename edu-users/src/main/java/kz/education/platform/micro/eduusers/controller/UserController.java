package kz.education.platform.micro.eduusers.controller;



import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduusers.models.dto.UserDTO;
import kz.education.platform.micro.eduusers.models.dto.UserUpdateDTO;
import kz.education.platform.micro.eduusers.models.search.UserSearchValues;
import kz.education.platform.micro.eduusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update (@Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                              @PathVariable("id") Long id){

        userService.updateUser(id,userUpdateDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<UserDTO>> search(@RequestBody UserSearchValues userSearchValues){
        Page<UserDTO> result = userService.findUserByParams(userSearchValues);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.saveUser(userUpdateDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return  ResponseEntity.ok().body(userService.getUserById(id));
    }
}
