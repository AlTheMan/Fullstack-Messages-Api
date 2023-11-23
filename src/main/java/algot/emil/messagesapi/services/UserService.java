package algot.emil.messagesapi.services;


import algot.emil.messagesapi.entities.user.AppUser;
import algot.emil.messagesapi.repositories.PatientRepository;
import algot.emil.messagesapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {


	private final UserRepository userRepository;
	private final PatientRepository patientRepository;
	private final DoctorService doctorService;

	@Autowired
	public UserService(UserRepository userRepository, PatientRepository patientRepository, DoctorService doctorService) {
		this.patientRepository = patientRepository;
		this.userRepository = userRepository;
		this.doctorService = doctorService;
	}

	/**
	 * @param email user email
	 * @return null if no user was found
	 */

	public Long getUserIdFromEmail(String email) {
		Optional<AppUser> user = userRepository.findAppUserByEmail(email);
		return user.map(AppUser::getId).orElse(null);

	}

}
