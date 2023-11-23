package algot.emil.messagesapi.services;

import algot.emil.entities.Patient;
import algot.emil.messagesapi.dto.*;
import algot.emil.messagesapi.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
	private final PatientRepository patientRepository;
	private final DoctorService doctorService;
	private final StaffService staffService;

	public PatientService(PatientRepository patientRepository, DoctorService doctorService, StaffService staffService) {
		this.patientRepository = patientRepository;
		this.doctorService = doctorService;
		this.staffService = staffService;
	}

	public PatientDTO getPatientById(long id) {
		Optional<Patient> optPatient = patientRepository.findById(id);

		if (optPatient.isEmpty()) return null;

		PatientDTO dto = new PatientDTO();

		dto.setId(optPatient.get().getId());
		dto.setSex(optPatient.get().getSex());
		dto.setBirthdate(optPatient.get().getBirthdate());
		dto.setFirstName(optPatient.get().getFirstName());
		dto.setLastName(optPatient.get().getLastName());

		return dto;
	}
}
