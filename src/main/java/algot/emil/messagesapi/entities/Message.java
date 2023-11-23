package algot.emil.messagesapi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table
@Entity
public class Message extends BaseEntity {


    private LocalDate time;
    private long employeeId;
    private long patientId;

    private long sentById; //from whom the message was sent from

    //@ManyToOne
    //private AppUser loggedInUser;
    private String message;

    public Message(long employeeId, long patientId, long sentById, String message) {
        this.time = LocalDate.now();
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.sentById = sentById;
        this.message = message;
    }

    public Message() {

    }

    public long getSentById() {
        return sentById;
    }

    public LocalDate getTime() {
        return time;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getPatientId() {
        return patientId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time=" + time +
                ", employeeId=" + employeeId +
                ", patientId=" + patientId +
                ", sentById=" + sentById +
                ", message='" + message + '\'' +
                '}';
    }
}
