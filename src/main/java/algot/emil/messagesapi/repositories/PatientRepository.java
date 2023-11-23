package algot.emil.messagesapi.repositories;

import algot.emil.entities.Patient;
import algot.emil.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


	//@Query(value = "select p.id from Patient p where p.email = ?1")
	//public Long getPatientIdByEmail(String email);




}
