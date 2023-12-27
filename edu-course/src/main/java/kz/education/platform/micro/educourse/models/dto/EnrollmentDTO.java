package kz.education.platform.micro.educourse.models.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentDTO {

    private Long id;

    private LocalDateTime enrollmentDate;

    private Long userId;


    private CourseDTO course;
}
