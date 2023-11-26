package algot.emil.messagesapi.services;


import algot.emil.entities.Doctor;
import algot.emil.messagesapi.dto.StaffDTO;
import algot.emil.entities.Staff;
import algot.emil.enums.UserPrivilege;
import algot.emil.messagesapi.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
	private final StaffRepository staffRepository;

	public StaffService(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	public List<StaffDTO> getAllStaff() {
		List<Staff> staffs = staffRepository.findAll();
		List<StaffDTO> staffDTOs = new ArrayList<>();
		for (Staff d : staffs) {
			UserPrivilege privilege;
			if (d instanceof Doctor) {
				privilege = UserPrivilege.DOCTOR;
			} else {
				privilege = UserPrivilege.STAFF;
			}
			StaffDTO s = new StaffDTO(d.getFirstName(), d.getLastName(), d.getId(), privilege);
			staffDTOs.add(s);
		}
		return staffDTOs;
	}

	public List<StaffDTO> GetStaffById(List<Long> staffIdList) {
		List<Staff> staff = staffRepository.findAllById(staffIdList);
		ArrayList<StaffDTO> staffDTO = new ArrayList<>();
		for (Staff s : staff) {
			UserPrivilege privilege;
			if (s instanceof Doctor) {
				privilege = UserPrivilege.DOCTOR;
			} else {
				privilege = UserPrivilege.STAFF;
			}

			StaffDTO dto = new StaffDTO(s.getFirstName(), s.getLastName(), s.getId(), privilege);
			System.out.println(dto.privilege());
			staffDTO.add(dto);
		}
		return staffDTO;
	}

	public Staff getStaffById(long writtenBy) {
		Optional<Staff> staff = staffRepository.findById(writtenBy);
		return staff.orElse(null);

	}

}
