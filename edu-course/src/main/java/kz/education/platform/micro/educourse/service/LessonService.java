package kz.education.platform.micro.educourse.service;

import kz.education.platform.micro.educourse.mapper.LessonMapper;
import kz.education.platform.micro.educourse.models.dto.LessonDTO;
import kz.education.platform.micro.educourse.repository.LessonRepository;
import kz.education.platform.micro.educourse.utils.exception.NotFoundException;
import kz.education.platform.micro.eduentity.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonService {

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;


    public List<LessonDTO> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();

        if (lessons.isEmpty()) {
            throw new NotFoundException("No lessons found");
        }

        return lessons.stream().map(lessonMapper::convertToDTO).collect(Collectors.toList());
    }

    public LessonDTO findById(Long id) {
        return lessonMapper.convertToDTO(lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson not found with id " + id)));
    }

    @Transactional
    public void saveLesson(LessonDTO lessonDTO){
        lessonRepository.save(lessonMapper.convertToLesson(lessonDTO));
    }

    @Transactional
    public void deleteLesson(Long id) {
        findById(id);
        lessonRepository.deleteById(id);
    }


    @Transactional
    public void updateLesson(Long lessonId, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() ->new NotFoundException("Lesson not found with id " + lessonId));

        Lesson updateLesson = lessonMapper.convertToLesson(lessonDTO);

        updateLesson.setId(lesson.getId());

        lessonRepository.save(updateLesson);
    }


}
