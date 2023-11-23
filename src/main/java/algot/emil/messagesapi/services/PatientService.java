package algot.emil.messagesapi.services;

import algot.emil.messagesapi.dto.*;
import algot.emil.messagesapi.entities.people.Doctor;
import algot.emil.messagesapi.entities.people.PatientEntity;
import algot.emil.messagesapi.entities.people.PersonEntity;
import algot.emil.messagesapi.entities.people.Staff;
import algot.emil.messagesapi.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	private final PatientRepository patientRepository;
	private final DoctorService doctorService;
	private final StaffService staffService;
	private final FhirService fhirService;

	public PatientService(PatientRepository patientRepository, DoctorService doctorService, StaffService staffService, FhirService fhirService) {
		this.patientRepository = patientRepository;
		this.doctorService = doctorService;
		this.staffService = staffService;
		this.fhirService = fhirService;
	}

	public PatientDTO getPatientFromFhirByPatientId(long id) {
		var optPatient = patientRepository.findById(id);
		if (optPatient.isEmpty()) return null;

		PatientEntity patientEntity = optPatient.get();
		String fhirId = patientEntity.getFhirId();
		PatientFhirDTO p = fhirService.getPatientByFhirId(fhirId);
		PatientDTO patientDTO = new PatientDTO(p.getFirstName(), p.getFamilyName(), p.getSex(), id, p.getBirthdate());
		return patientDTO;
	}
}
