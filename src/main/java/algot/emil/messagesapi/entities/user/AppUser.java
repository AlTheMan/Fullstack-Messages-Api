package algot.emil.messagesapi.entities.user;

import algot.emil.messagesapi.entities.BaseEntity;
import algot.emil.messagesapi.enums.UserPrivilege;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Note: This class must be called AppUser instead of App because of name clash. Program will not compile otherwise
 */

@Entity
public class AppUser extends BaseEntity {


	@Column
	private UserPrivilege privilege;
	@Column(unique = true) // Detta gör email-kolumnen unik i databasen
	private String email;
	private String password;

	public AppUser(UserPrivilege privilege, String email, String password) {
		this.privilege = privilege;
		this.email = email;
		this.password = password;
	}

	public AppUser() {
	}

	public UserPrivilege getPrivilege() {
		return privilege;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", privilege=" + privilege +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
