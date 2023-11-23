package algot.emil.messagesapi.services;


import algot.emil.messagesapi.dto.NameDTO;
import algot.emil.messagesapi.dto.StaffDTO;
import algot.emil.messagesapi.entities.people.Staff;
import algot.emil.messagesapi.enums.UserPrivilege;
import algot.emil.messagesapi.repositories.StaffRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
	private final StaffRepository repository;

	public StaffService(StaffRepository repository) {
		this.repository = repository;
	}

	public Long getStaffIdByUserId(Long userId){
		if(userId<0) return -1L;
		Staff staff= repository.getStaffByAppUser_Id(userId);
		if (staff==null){
			return -1L;
		}
		return staff.getId();
	}

	public List<StaffDTO> getAllStaff() {
		List<Staff> staffs = repository.findAll();
		List<StaffDTO> staffDTOs = new ArrayList<>();
		for (Staff d : staffs) {
			StaffDTO s = new StaffDTO(d.getFirstName(), d.getLastName(), d.getId(), UserPrivilege.STAFF);
			staffDTOs.add(s);
		}
		return staffDTOs;
	}

	public NameDTO getStaffNameById(long id){
		var opt = repository.findById(id);
		if (opt.isEmpty()) return null;

		NameDTO dto = new NameDTO();
		dto.setFirstName(opt.get().getFirstName());
		dto.setLastName(opt.get().getLastName());
		return dto;

	}

	public List<StaffDTO> GetStaffById(List<Long> staffIdList) {
		List<Staff> staff = repository.findAllById(staffIdList);
		ArrayList<StaffDTO> staffDTO = new ArrayList<>();
		for (Staff s : staff) {
			StaffDTO dto = new StaffDTO(s.getFirstName(), s.getLastName(), s.getId(), UserPrivilege.DOCTOR);
			staffDTO.add(dto);
		}
		return staffDTO;
	}

	public Staff getStaffById(long writtenBy) {
		Optional<Staff> staff = repository.findById(writtenBy);
		return staff.orElse(null);

	}

}
