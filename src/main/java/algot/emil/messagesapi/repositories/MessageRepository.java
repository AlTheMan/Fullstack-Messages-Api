package algot.emil.messagesapi.repositories;

import algot.emil.messagesapi.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //TODO: har inte testat
    //List<Message> findAllByIdContainsAndIdContainsOrderByTime(Long staffId, Long patientId);

    List<Message> findAllByPatientIdAndEmployeeIdOrderByTime(Long patientId, Long staffId);

    List<Message> findAllByEmployeeIdAndPatientIdOrderByTime(Long patientId, Long staffId);

    @Query("SELECT DISTINCT m.employeeId FROM Message m WHERE m.patientId = ?1")
    List<Long> findDistinctEmployeeIdsByPatientId(Long patientId);

    @Query("SELECT DISTINCT m.patientId FROM Message m WHERE m.employeeId = ?1")
    List<Long> findDistinctPatientIdsByEmployeeId(Long employeeId);

}

