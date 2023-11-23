package algot.emil.messagesapi.dto;

public class DoctorDTO {

	private long id;
	private String firstName;
	private String lastName;
	private final String prefix;

	public DoctorDTO(long id, String firstName, String lastName, String prefix) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.prefix = prefix;
	}


	public DoctorDTO() {
		this.prefix = "Dr";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String toString() {
		return "DoctorDTO{" +
				"givenNames=" + firstName +
				", lastName='" + lastName + '\'' +
				", prefix='" + prefix + '\'' +
				'}';
	}
}
