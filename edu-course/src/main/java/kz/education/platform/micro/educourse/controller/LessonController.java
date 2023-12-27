package kz.education.platform.micro.educourse.controller;

import kz.education.platform.micro.educourse.models.dto.LessonDTO;
import kz.education.platform.micro.educourse.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getById(@PathVariable("id") Long id ){
        return ResponseEntity.ok((lessonService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LessonDTO>> allCourse(){
        return ResponseEntity.ok((lessonService.findAll()));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@RequestBody LessonDTO lessonDTO) {
        lessonService.saveLesson(lessonDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        lessonService.deleteLesson(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody LessonDTO lessonDTO,
                                             @PathVariable("id") Long id) {
        lessonService.updateLesson(id, lessonDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

}
