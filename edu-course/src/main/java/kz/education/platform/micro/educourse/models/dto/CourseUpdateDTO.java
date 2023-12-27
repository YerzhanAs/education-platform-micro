package kz.education.platform.micro.educourse.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CourseUpdateDTO {


    @Size(min=2, max=100, message = "The length of name course must be between 2 and 100 characters.")
    private String nameCourse;

    @Size(min=2, max=500, message = "The length of name course must be between 2 and 500 characters.")
    private String description;
    private int levelDifficult;

    private String link;
    private LocalDateTime dateCreated;
}
