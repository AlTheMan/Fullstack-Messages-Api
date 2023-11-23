package algot.emil.messagesapi.repositories;

import algot.emil.messagesapi.entities.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	//Optional<AppUser> findAppUserByUsername(String username);

	//@Query("SELECT u FROM PatientUser u WHERE u.fhirId = :fhirId")
	//Optional<PatientUser> findAppUserByFhirId(String fhirId);

	Optional<AppUser> findAppUserByEmail(String email);


}
