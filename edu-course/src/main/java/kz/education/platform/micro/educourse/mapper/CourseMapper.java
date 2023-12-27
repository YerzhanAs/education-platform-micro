package kz.education.platform.micro.educourse.mapper;

import kz.education.platform.micro.educourse.models.dto.CourseDTO;
import kz.education.platform.micro.educourse.models.dto.CourseUpdateDTO;
import kz.education.platform.micro.eduentity.entity.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseDTO convertToDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }

    public Course convertToCourse(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    public Course convertToCourse(CourseUpdateDTO courseUpdateDTO) {
        return modelMapper.map(courseUpdateDTO, Course.class);
    }

    public CourseDTO convertToUpdateDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }


}
