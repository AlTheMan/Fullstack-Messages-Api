package algot.emil.messagesapi.entities.people;

import algot.emil.messagesapi.entities.user.AppUser;
import jakarta.persistence.*;

@Entity
@Table
public class Staff extends PersonEntity {

	//private String firstName;
	//private String lastName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	public Staff() {
		super();

	}

	public Staff(String firstName, String lastName, AppUser user) {
		super(firstName, lastName);
		this.appUser = user;
	}

	public Staff(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public String toString() {
		return "Staff{" +
				"id=" + id +
				", firstName='" + super.getFirstName() + '\'' +
				", lastName='" + super.getLastName() + '\'' +
				'}';
	}
}


