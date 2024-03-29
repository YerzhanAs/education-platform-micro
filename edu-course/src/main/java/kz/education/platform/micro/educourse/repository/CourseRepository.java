package kz.education.platform.micro.educourse.repository;


import kz.education.platform.micro.eduentity.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findAll();
}
