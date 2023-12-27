package kz.education.platform.micro.educourse.controller;

import kz.education.platform.micro.educourse.models.dto.CourseDTO;
import kz.education.platform.micro.educourse.models.dto.CourseUpdateDTO;
import kz.education.platform.micro.educourse.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable("id") Long id ){
        return ResponseEntity.ok((courseService.findById(id)));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> allCourse(){
        return ResponseEntity.ok((courseService.findAll()));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@RequestBody CourseUpdateDTO courseDTO) {
        courseService.saveCourse(courseDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody CourseUpdateDTO courseUpdateDTO,
                                             @PathVariable("id") Long id) {
        courseService.updateCourse(id, courseUpdateDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }


}