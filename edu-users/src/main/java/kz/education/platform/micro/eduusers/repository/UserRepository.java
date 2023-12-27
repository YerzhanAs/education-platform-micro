package kz.education.platform.micro.eduusers.repository;

import kz.education.platform.micro.eduentity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    @Query("SELECT u FROM User u where "+
            "(:email is null or :email='' or lower(u.email) like lower(concat('%', :email,'%'))) or"+
            "(:username is null or :username='' or lower(u.username) like lower(concat('%', :username,'%')))"
    )
    Page<User> findByParams(@Param("email") String email,
                            @Param("username") String username,
                            Pageable pageable);
}
