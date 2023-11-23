package algot.emil.messagesapi.dto;

import algot.emil.messagesapi.enums.Sex;

import java.time.LocalDate;

public class PatientFhirDTO {

    private String firstName;
    private String familyName;

    private Sex sex;
    private Long patientId;
    private String fhirId;

    private LocalDate birthdate;

    public PatientFhirDTO(String firstName, String familyName, Sex sex, LocalDate birthdate) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.fhirId=null;
        this.patientId=-1L;
    }

    public PatientFhirDTO() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFhirId() {
        return fhirId;
    }

    public void setFhirId(String fhirId) {
        this.fhirId = fhirId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "PatientFhirDTO{" +
                "firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", sex=" + sex +
                ", patientId=" + patientId +
                ", fhirId='" + fhirId + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
