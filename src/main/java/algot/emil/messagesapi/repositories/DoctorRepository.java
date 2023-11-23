package algot.emil.messagesapi.repositories;

import algot.emil.messagesapi.entities.people.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    public Doctor getDoctorByAppUser_Id(Long appUserId);

    public Optional<Doctor> getDoctorByFhirId(String fhirId);

    @Modifying
    @Query("update Doctor d set d.appUser.id = :userId where d.id = :doctorId")
    void updateAppUserInDoctor(@Param("userId") Long userId, @Param("doctorId") Long doctorId);


    public Optional<Doctor> getDoctorById(Long id);

    public Optional<Doctor> getDoctorByFirstName(String name);

}
