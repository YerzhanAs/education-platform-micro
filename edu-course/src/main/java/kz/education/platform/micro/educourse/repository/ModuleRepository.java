package kz.education.platform.micro.educourse.repository;

import kz.education.platform.micro.eduentity.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
