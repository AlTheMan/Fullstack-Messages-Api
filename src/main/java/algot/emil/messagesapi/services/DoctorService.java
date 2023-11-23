package algot.emil.messagesapi.services;

import algot.emil.journalapi.dto.DoctorDTO;
import algot.emil.journalapi.dto.NameDTO;
import algot.emil.journalapi.dto.RegisterStaffDTO;
import algot.emil.journalapi.dto.StaffDTO;
import algot.emil.journalapi.entities.people.Doctor;
import algot.emil.journalapi.enums.UserPrivilege;
import algot.emil.journalapi.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

	private final DoctorRepository repository;
	private final FhirService fhirService;

	public DoctorService(DoctorRepository repository, FhirService fhirService) {
		this.fhirService = fhirService;
		this.repository = repository;
	}

	public Long getDoctorIdByUserId(Long userId) {
		if (userId < 0) return -1L;
		Doctor doctor = repository.getDoctorByAppUser_Id(userId);
		if (doctor == null) {
			return -1L;
		}
		return doctor.getId();
	}

	public Doctor getDoctorByUserId(long userId) {
		 return repository.getDoctorByAppUser_Id(userId);
	}

	public List<StaffDTO> getDoctorsById(List<Long> staffIdList) {
		List<Doctor> doctors = repository.findAllById(staffIdList);
		ArrayList<StaffDTO> staffDTO = new ArrayList<>();
		for (Doctor doc : doctors) {
			StaffDTO dto = new StaffDTO(doc.getFirstName(), doc.getLastName(), doc.getId(), UserPrivilege.DOCTOR);
			staffDTO.add(dto);
		}
		return staffDTO;
	}

	public List<Long> getDoctorIdsByFhirIds(List<Long> fhirIds) {
		List<Long> ids = new ArrayList<>();
		for (Long fhirId : fhirIds) {
			Optional<Doctor> doctor = repository.getDoctorByFhirId(fhirId.toString());
			doctor.ifPresent(value -> ids.add(value.getId()));
		}
		return ids;
	}

	public NameDTO getDoctorNameById(long id) {
		var doctor = repository.findById(id);
		if (doctor.isEmpty()) return null;
		NameDTO dto = new NameDTO();
		dto.setFirstName(doctor.get().getFirstName());
		dto.setLastName(doctor.get().getLastName());
		return dto;
	}


	public List<Long> getDoctorFhirIdsAsLongByIds(List<Long> doctorIds) {
		List<Long> doctorFhirIds = new ArrayList<>();

		for (Long doctorId : doctorIds) {
			Optional<Doctor> doctor = repository.getDoctorById(doctorId);
			if (doctor.isPresent()) {
				try {
					doctorFhirIds.add(Long.parseLong(doctor.get().getFhirId()));
				} catch (NumberFormatException e) {
					doctorFhirIds.add(36050527L); // known id
				}
			}
		}
		return doctorFhirIds;
	}

	public List<StaffDTO> getAllDoctors() {
		List<Doctor> doctors = repository.findAll();
		List<StaffDTO> staffDTOs = new ArrayList<>();
		for (Doctor d : doctors) {
			StaffDTO s = new StaffDTO(d.getFirstName(), d.getLastName(), d.getId(), UserPrivilege.DOCTOR);
			staffDTOs.add(s);
		}
		return staffDTOs;
	}


	public boolean addDoctor(DoctorDTO dto) {
		String fhirId = fhirService.addDoctorToFhir(dto);
		if (fhirId == null) return false;
		System.out.println(fhirId);
		return true;
	}

	@Transactional
	public Long addDoctorAndRegister(RegisterStaffDTO regDTO) {

		//idt i doctorDTO för fhirService är till för att man ska kunna koppla en identifikator för fhir
		//vi ska kunna göra bulk requests.
		DoctorDTO dto = new DoctorDTO(-1, regDTO.firstName(),regDTO.lastName(),"Dr.");
		String fhirId = fhirService.addDoctorToFhir(dto);
		if (fhirId == null) return -1L;

		Doctor doctor = new Doctor(dto.getFirstName(),dto.getLastName(),fhirId);
		repository.save(doctor);
		Optional<Doctor> doctor2 = repository.getDoctorByFhirId(fhirId);
		if(doctor2!=null && doctor2.isPresent()){
			repository.updateAppUserInDoctor(regDTO.userId(),doctor2.get().getId());
			System.out.println("DoctorService- Saved doctor. fihrId: " + fhirId + ", doctor id:"+ doctor.getId() + ", userId: "+regDTO.userId());
			return doctor2.get().getId();
		}else{
			System.out.println("DoctorService - could not save doctor to repository.");
			return -1L;
		}
	}

	public List<DoctorDTO> getDoctorsByFhirIds(List<Long> fhirIds) {
		List<DoctorDTO> doctorDTOS = new ArrayList<>();
		for (Long l : fhirIds) {
			Optional<Doctor> doctor = repository.getDoctorByFhirId(l.toString());

			if (doctor.isPresent()) {
				DoctorDTO dto = new DoctorDTO();
				Doctor d = doctor.get();
				dto.setId(d.getId());
				dto.setFirstName(d.getFirstName());
				dto.setLastName(d.getLastName());
				doctorDTOS.add(dto);
			}
		}
		if (doctorDTOS.isEmpty()) {

			Optional<Doctor> d = repository.getDoctorByFirstName("Anders");
			if (d.isPresent()) {
				DoctorDTO dummy = new DoctorDTO();
				dummy.setFirstName(d.get().getFirstName());
				dummy.setLastName(d.get().getLastName());
				dummy.setId(d.get().getId());
				doctorDTOS.add(dummy);
			}
		}

		return doctorDTOS;
	}

	public Doctor getDoctorById(long writtenBy) {
		Optional<Doctor> doctor = repository.getDoctorById(writtenBy);
		return doctor.orElse(null);
	}
}
