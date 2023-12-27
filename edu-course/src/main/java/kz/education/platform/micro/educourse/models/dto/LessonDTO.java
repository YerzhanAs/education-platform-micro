package kz.education.platform.micro.educourse.models.dto;

import kz.education.platform.micro.eduentity.entity.Module;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {

    private Long id;

    private String lessonName;

    private int step;

    private String videoUrl;
    private String lessonText;

    private ModuleDTO module;

}
