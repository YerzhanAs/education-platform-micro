package kz.education.platform.micro.educourse.controller;

import kz.education.platform.micro.educourse.models.dto.EnrollmentDTO;
import kz.education.platform.micro.educourse.models.response.EnrollResponse;
import kz.education.platform.micro.educourse.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<HttpStatus> save(@RequestBody EnrollResponse enrollResponse) {
        enrollmentService.enrollUserInCourse(enrollResponse);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users-enroll/{id}")
    public ResponseEntity<List<EnrollmentDTO>> save(@PathVariable("id") Long userId) {
        return  ResponseEntity.ok(enrollmentService.userEnrollmetList(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        enrollmentService.deleteEnrollment(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
}
