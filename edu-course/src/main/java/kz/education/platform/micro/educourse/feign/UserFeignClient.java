package kz.education.platform.micro.educourse.feign;


import kz.education.platform.micro.educourse.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="edu-users", fallback = UserFeignClientFallback.class)
@Component
public interface UserFeignClient {

    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id);
}


