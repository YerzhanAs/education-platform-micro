package kz.education.platform.micro.educourse.feign;

import kz.education.platform.micro.educourse.models.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
class UserFeignClientFallback implements UserFeignClient {

    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        return null;
    }
}
