package kz.education.platform.micro.educourse.models.dto;

import kz.education.platform.micro.eduentity.entity.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ModuleDTO {

    private Long id;

    private String moduleName;


    private String moduleDescription;

    private LocalDateTime dateCreated;

    private CourseDTO course;
}
