package kz.education.platform.micro.eduusers.repository;

import kz.education.platform.micro.eduentity.entity.ERole;
import kz.education.platform.micro.eduentity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole eRole);
}
