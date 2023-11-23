package algot.emil.messagesapi.dto;


import algot.emil.messagesapi.enums.Sex;

import java.time.LocalDate;

public class PatientDTO {

    private String firstName;
    private String familyName;

    private Sex sex;
    private Long patientId;

    private LocalDate birthdate;

    public PatientDTO(String firstName, String familyName, Sex sex, LocalDate birthdate) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.patientId=-1L;
    }

    public PatientDTO(String firstName, String familyName, Sex sex, Long patientId, LocalDate birthdate) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.sex = sex;
        this.patientId = patientId;
        this.birthdate = birthdate;
    }

    public PatientDTO() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
