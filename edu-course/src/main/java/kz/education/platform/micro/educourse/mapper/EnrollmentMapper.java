package kz.education.platform.micro.educourse.mapper;


import kz.education.platform.micro.educourse.models.dto.EnrollmentDTO;
import kz.education.platform.micro.educourse.models.dto.LessonDTO;
import kz.education.platform.micro.eduentity.entity.Enrollment;
import kz.education.platform.micro.eduentity.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final ModelMapper modelMapper;

    public EnrollmentDTO convertToDTO(Enrollment enrollment) {
        return modelMapper.map(enrollment,EnrollmentDTO.class);
    }

    public Enrollment convertToEnrollment(EnrollmentDTO enrollmentDTO) {
        return modelMapper.map(enrollmentDTO, Enrollment.class);
    }
}
