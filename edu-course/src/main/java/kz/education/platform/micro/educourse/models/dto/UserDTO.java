package kz.education.platform.micro.educourse.models.dto;


import kz.education.platform.micro.eduentity.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotEmpty(message = "The name address is required.")
    @Size(min=2, max=100, message = "The length of username must be between 2 and 100 characters.")
    private String username;

    @NotEmpty(message = "The first name  is required.")
    @Size(min=2, max=100, message = "The length of first name must be between 2 and 100 characters.")
    private String firstName;

    @NotEmpty(message = "The last name  is required.")
    @Size(min=2, max=100, message = "The length of last name must be between 2 and 100 characters.")
    private String lastName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid")
    private String email;

    private int age;

    @NotEmpty(message = "The password  is required.")
    private String password;

    private List<Role> roles;
}
