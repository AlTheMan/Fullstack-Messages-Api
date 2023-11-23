package algot.emil.messagesapi.entities.people;

import algot.emil.messagesapi.entities.user.AppUser;
import jakarta.persistence.*;

@Entity
@Table
public class Doctor extends PersonEntity {



	// Needed for encounters..
	@Column(unique = true, nullable = true, name = "fhir_id")
	private String fhirId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	public Doctor() {

	}

	public Doctor(String firstName, String lastName, AppUser appUser) {
		super(firstName, lastName);
		this.appUser = appUser;
	}

	public Doctor(String firstName, String lastName, String fhirId, AppUser appUser) {
		super(firstName, lastName);
		this.fhirId = fhirId;
		this.appUser = appUser;
	}

	public Doctor(String firstName, String lastName, String fhirId) {
		super(firstName, lastName);
		this.fhirId = fhirId;
	}


	public String getFhirId() {
		return fhirId;
	}

	public void setFhirId(String fhirId) {
		this.fhirId = fhirId;
	}



	@Override
	public String toString() {
		return "Doctor{" +
				"id=" + id +
				", firstName='" + super.getFirstName() + '\'' +
				", lastName='" + super.getLastName() + '\'' +
				'}';
	}
}
