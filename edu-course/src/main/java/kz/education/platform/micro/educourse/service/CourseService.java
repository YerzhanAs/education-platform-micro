package kz.education.platform.micro.educourse.service;


import kz.education.platform.micro.educourse.mapper.CourseMapper;
import kz.education.platform.micro.educourse.models.dto.CourseDTO;
import kz.education.platform.micro.educourse.models.dto.CourseUpdateDTO;
import kz.education.platform.micro.educourse.repository.CourseRepository;
import kz.education.platform.micro.educourse.utils.exception.NotFoundException;
import kz.education.platform.micro.eduentity.entity.Course;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }

        return courses.stream().map(courseMapper::convertToDTO).collect(Collectors.toList());
    }

    public CourseDTO findById(Long id) {
        return courseMapper.convertToDTO(courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id " + id)));
    }


    @Transactional
    public void saveCourse(CourseUpdateDTO courseUpdateDTO){
        courseUpdateDTO.setDateCreated(LocalDateTime.now());
        courseRepository.save(courseMapper.convertToCourse(courseUpdateDTO));
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        findById(courseId);
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, CourseUpdateDTO courseUpdateDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->new NotFoundException("Course not found with id " + courseId));

        Course updatedCourse = courseMapper.convertToCourse(courseUpdateDTO);

        updatedCourse.setId(course.getId());
        updatedCourse.setDateModified(LocalDateTime.now());

         courseRepository.save(updatedCourse);
    }




}
