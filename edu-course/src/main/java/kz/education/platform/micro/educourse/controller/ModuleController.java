package kz.education.platform.micro.educourse.controller;

import kz.education.platform.micro.educourse.models.dto.ModuleDTO;
import kz.education.platform.micro.educourse.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/module")
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDTO> getById(@PathVariable("id") Long id ){
        return ResponseEntity.ok((moduleService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModuleDTO>> allCourse(){
        return ResponseEntity.ok((moduleService.findAll()));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@RequestBody ModuleDTO moduleDTO) {
        moduleService.saveModule(moduleDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        moduleService.deleteModule(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody ModuleDTO moduleDTO,
                                             @PathVariable("id") Long id) {
        moduleService.updateModule(id, moduleDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
}
