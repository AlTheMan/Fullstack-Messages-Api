package algot.emil.messagesapi.services;


import algot.emil.messagesapi.dto.NameDTO;
import algot.emil.messagesapi.dto.RegisterStaffDTO;
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

	@Transactional
    public Long addStaff(RegisterStaffDTO dto) {

		Staff staff = new Staff(dto.firstName(),dto.lastName());
		repository.save(staff);
		Optional<Staff> staff2 = repository.findStaffByFirstNameAndLastName(dto.firstName(), dto.lastName());

		//alternativt: för att hitta den senaste staffen via firstName och lastName, så man kan ha flera staff med first name och last name:
		/*
		List<Staff> staffList = repository.findStaffByFirstNameAndLastNameList(dto.firstName(), dto.lastName());
		Staff latestStaff = null;
		if (!staffList.isEmpty()) {
			latestStaff = staffList.getLast(); // The first staff member in the list is the latest one
			repository.updateAppUserInStaff(dto.userId(),latestStaff.getId());
			return latestStaff.getId();
		}*/


		if(staff2!=null && staff2.isPresent()){
			repository.updateAppUserInStaff(dto.userId(),staff2.get().getId());
			System.out.println("StaffService - Saved Staff. staffId:"+ staff2.get().getId() + ", userId: "+dto.userId());
			return staff2.get().getId();
		}else{
			System.out.println("StaffService - could not save staff to repository.");
			return -1L;
		}
    }
}
