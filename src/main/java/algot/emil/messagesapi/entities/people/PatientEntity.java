package algot.emil.messagesapi.entities.people;

import algot.emil.messagesapi.entities.BaseEntity;

import algot.emil.messagesapi.entities.user.AppUser;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "patient")
@Entity
public class PatientEntity extends BaseEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	@Column(unique = true, nullable = true, name = "fhir_id")
	private String fhirId;


	public PatientEntity(String fhirId) {
		this.fhirId = fhirId;
	}

	public PatientEntity(AppUser appUser, String fhirId) {
		this.appUser = appUser;
		this.fhirId = fhirId;
	}

	public PatientEntity() {
	}


	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getFhirId() {
		return fhirId;
	}

	public void setFhirId(String fhirId) {
		this.fhirId = fhirId;
	}


}
