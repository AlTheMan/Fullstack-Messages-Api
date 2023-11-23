package algot.emil.messagesapi.services;


import algot.emil.entities.User;
import algot.emil.messagesapi.dto.NameDTO;
import algot.emil.messagesapi.dto.StaffDTO;
import algot.emil.entities.Staff;
import algot.emil.enums.UserPrivilege;
import algot.emil.messagesapi.repositories.StaffRepository;
import algot.emil.messagesapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
	private final StaffRepository repository;
	private final UserRepository userRepository;

	public StaffService(StaffRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	public Long getStaffIdByUserId(Long userId){
		if(userId<0) return -1L;
		Optional<User> user = userRepository.findById(userId);
		if(user==null ||user.isEmpty()) return -1L;
		Optional<Staff> staff= repository.findById(user.get().getId());
		if (staff==null || staff.isEmpty()){
			return -1L;
		}
		return staff.get().getId();
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
