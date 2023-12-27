package kz.education.platform.micro.educourse.service;


import kz.education.platform.micro.educourse.feign.UserFeignClient;
import kz.education.platform.micro.educourse.mapper.EnrollmentMapper;
import kz.education.platform.micro.educourse.models.dto.EnrollmentDTO;
import kz.education.platform.micro.educourse.models.dto.UserDTO;
import kz.education.platform.micro.educourse.models.response.EnrollResponse;
import kz.education.platform.micro.educourse.repository.CourseRepository;
import kz.education.platform.micro.educourse.repository.EnrollmentRepository;
import kz.education.platform.micro.educourse.utils.exception.EnrollmentException;
import kz.education.platform.micro.educourse.utils.exception.NotFoundException;
import kz.education.platform.micro.eduentity.entity.Course;
import kz.education.platform.micro.eduentity.entity.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserFeignClient userFeignClient;
    private final EnrollmentMapper enrollmentMapper;

    public List<EnrollmentDTO> userEnrollmetList(Long userId){
        List<Enrollment> enrollments = enrollmentRepository.findAllByUserId(userId);

        if(enrollments.isEmpty()){
            throw new NotFoundException("No enrollments found with id "+userId);
        }

        return enrollments.stream().map(enrollmentMapper::convertToDTO).collect(Collectors.toList());
    }


    @Transactional
    public void enrollUserInCourse(EnrollResponse enrollResponse) {
        Course course = courseRepository.findById(enrollResponse.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id " + enrollResponse.getCourseId()));

        ResponseEntity<UserDTO> result = userFeignClient.getUserById(enrollResponse.getUserId());

        if(result == null){
            throw new NotFoundException("User not found with id " + enrollResponse.getUserId());
        }

        if (enrollmentRepository.existsByCourseAndAndUserId(course, enrollResponse.getUserId())) {
            throw new EnrollmentException("User is already enrolled in the course");
        }


        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUserId(enrollResponse.getUserId());
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollmentRepository.save(enrollment);

    }

    @Transactional
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
