package algot.emil.messagesapi.dto;

public class GetMessageDTO {
    private final Long employeeId;
    private final Long patientId;

    public GetMessageDTO(Long employeeId, Long patientId) {
        this.employeeId = employeeId;
        this.patientId = patientId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "GetMessageDTO{" +
                "staffId=" + employeeId +
                ", patientId=" + patientId +
                '}';
    }
}

