package kz.education.platform.micro.educourse.repository;

import kz.education.platform.micro.eduentity.entity.Course;
import kz.education.platform.micro.eduentity.entity.Enrollment;
import kz.education.platform.micro.eduentity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

//    boolean existsByUserAndCourse(User user, Course course);

    boolean existsByCourseAndAndUserId(Course course, Long userId);

    List<Enrollment>  findAllByUserId(Long userId);


}
