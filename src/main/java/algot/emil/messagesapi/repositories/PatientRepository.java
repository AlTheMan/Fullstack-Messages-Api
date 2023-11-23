package algot.emil.messagesapi.repositories;

import algot.emil.messagesapi.entities.people.PatientEntity;
import algot.emil.messagesapi.entities.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {


	//@Query(value = "select p.id from Patient p where p.email = ?1")
	//public Long getPatientIdByEmail(String email);

	public Optional<PatientEntity> getPatientEntityByAppUser(AppUser user);

	@Modifying
	@Query("update PatientEntity p set p.appUser.id = :userId where p.id = :patientId")
	void updateAppUserInPatientEntity(@Param("userId") Long userId, @Param("patientId") Long patientId);

	public Optional<PatientEntity> getPatientEntityByFhirId(String id);
	public Optional<PatientEntity> getPatientEntitiesByAppUser_Id(Long id);
	public Optional<PatientEntity> findByAppUser_Id(Long id);


}
