package kz.education.platform.micro.eduusers.models.dto;

import kz.education.platform.micro.eduentity.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class UserContactDTO {

    private String mobileNumber;

    private String region;

    private String city;

    private String address;

    private UserDTO user;
}
