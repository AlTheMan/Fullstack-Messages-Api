package algot.emil.messagesapi.services;

import algot.emil.messagesapi.dto.DoctorDTO;
import algot.emil.messagesapi.dto.NameDTO;
import algot.emil.messagesapi.dto.StaffDTO;
import algot.emil.entities.Doctor;
import algot.emil.enums.UserPrivilege;
import algot.emil.messagesapi.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

	private final DoctorRepository repository;

	public DoctorService(DoctorRepository repository) {
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

	public NameDTO getDoctorNameById(long id) {
		var doctor = repository.findById(id);
		if (doctor.isEmpty()) return null;
		NameDTO dto = new NameDTO();
		dto.setFirstName(doctor.get().getFirstName());
		dto.setLastName(doctor.get().getLastName());
		return dto;
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

	public Doctor getDoctorById(long writtenBy) {
		Optional<Doctor> doctor = repository.getDoctorById(writtenBy);
		return doctor.orElse(null);
	}
}
