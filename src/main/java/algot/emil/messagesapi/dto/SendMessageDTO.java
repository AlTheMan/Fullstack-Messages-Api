package algot.emil.messagesapi.dto;

public class SendMessageDTO {
    private final Long employeeId;
    private final Long patientId;
    private final String message;
    private final Long senderId;

    public SendMessageDTO(Long employeeId, Long patientId, String message, Long senderId) {
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.message = message;
        this.senderId = senderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SendMessageDTO{" +
                "employeeId=" + employeeId +
                ", patientId=" + patientId +
                ", message='" + message + '\'' +
                ", senderId=" + senderId +
                '}';
    }
}
