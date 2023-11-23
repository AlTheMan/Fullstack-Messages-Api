package algot.emil.messagesapi.repositories;

import algot.emil.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    public Staff getStaffByAppUser_Id(Long appUserId);

    @Query("SELECT s FROM Staff s WHERE s.firstName = :firstName AND s.lastName = :lastName ORDER BY s.id DESC")
    List<Staff> findStaffByFirstNameAndLastNameList(@Param("firstName") String firstName, @Param("lastName") String lastName);

    Optional<Staff> findStaffByFirstNameAndLastName(String firstName, String lastName);

}
