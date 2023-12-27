package kz.education.platform.micro.educourse.mapper;

import kz.education.platform.micro.educourse.models.dto.LessonDTO;
import kz.education.platform.micro.eduentity.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    private final ModelMapper modelMapper;

    public LessonDTO convertToDTO(Lesson lesson) {
        return modelMapper.map(lesson, LessonDTO.class);
    }

    public Lesson convertToLesson(LessonDTO lessonDTO) {
        return modelMapper.map(lessonDTO, Lesson.class);
    }

}
