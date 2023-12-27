package kz.education.platform.micro.eduentity.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "lessons", schema = "courses", catalog = "edu_courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "step")
    private int step;

    @Column(name= "video_url")
    private String videoUrl;

    @Column(name="lesson_text")
    private String lessonText;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
