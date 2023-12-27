package kz.education.platform.micro.eduusers.repository;

import kz.education.platform.micro.eduentity.entity.User;
import kz.education.platform.micro.eduentity.entity.UserContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserContactRepository extends JpaRepository<UserContact, Long> {

    @Query("SELECT u FROM UserContact u where "+
            "(:address is null or :address='' or lower(u.address) like lower(concat('%', :address,'%'))) or"+
            "(:mobileNumber is null or :mobileNumber='' or lower(u.mobileNumber) like lower(concat('%', :mobileNumber,'%'))) or"+
            "(:city is null or :city='' or lower(u.city) like lower(concat('%', :city,'%'))) or"+
            "(:region is null or :region='' or lower(u.region) like lower(concat('%', :region,'%')))"
    )
    Page<UserContact> findByParams(@Param("address") String address,
                            @Param("mobileNumber") String mobileNumber,
                            @Param("city") String city,
                            @Param("region") String region,
                            Pageable pageable);

    UserContact findUserContactByUser(User user);
}
