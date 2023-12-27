package kz.education.platform.micro.eduentity.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses", schema = "courses", catalog = "edu_courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_course")
    private String nameCourse;

    @Column(name="description")
    private String description;

    @Column(name="date_created")
    private LocalDateTime dateCreated;

    @Column(name="date_modified")
    private LocalDateTime dateModified;

    @Column(name="level_difficult")
    private int levelDifficult;

    @Column(name="link")
    private String link;

    @OneToMany(mappedBy = "course")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Enrollment> enrollments;

}
