package algot.emil.messagesapi.controllers;


import algot.emil.messagesapi.dto.GetMessageDTO;
import algot.emil.messagesapi.dto.PatientDTO;
import algot.emil.messagesapi.dto.SendMessageDTO;
import algot.emil.messagesapi.dto.StaffDTO;
import algot.emil.entities.Message;
import algot.emil.messagesapi.services.*;
import org.springframework.web.bind.annotation.*;
import algot.emil.messagesapi.services.StaffService;
import algot.emil.messagesapi.services.DoctorService;
import algot.emil.messagesapi.services.MessageService;
import algot.emil.messagesapi.services.UserService;
import algot.emil.messagesapi.services.PatientService;


import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping({"/messages", "/messages/"})
public class MessageController {


    private final MessageService messageService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final StaffService staffService;
    private final PatientService patientService;

    public MessageController(MessageService messageService, UserService userService, DoctorService doctorService, StaffService staffService, PatientService patientService) {
        this.messageService = messageService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.staffService = staffService;
        this.patientService = patientService;
    }

    @PostMapping({"/send", "/send/"})
    public void sendMessage(@RequestBody SendMessageDTO dto) {

        messageService.sendMessage(dto);
    }


    /**
     * @return list of all staffs and doctors
     */
    @GetMapping("/getAllStaff")
    public List<StaffDTO> getAllStaff() {
	    //staff.addAll(doctorService.getAllDoctors());
        return staffService.getAllStaff();
    }


    /**
     * Returnerar en lista av alla patienter som har en meddelandekonversation kopplad till en läkare eller staff.
     * OBS: patienter får ej anropa denna.
     * id= läkares/staffs id. Ej user_id.
     */
    @GetMapping({"/getPatientMessageListByIdAsStaff", "/getPatientMessageListByIdAsStaff/"})
    public List<PatientDTO> getPatientMessageListByIdAsStaff(Long id) {
        List<Long> patientIdList = messageService.getPatientListById(id);
        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Long l : patientIdList) {
            PatientDTO p = patientService.getPatientById(l);
            patientDTOs.add(p);
        }
        return patientDTOs;

    }

    /**
     * Returnerar en lista av alla staff/läkare som har en meddelandekonversation kopplad till en patient.
     * OBS: läkare/staff får ej anropa denna.
     */
    @GetMapping({"/getPeopleMessageList", "/getPeopleMessageList/"})
    public List<StaffDTO> getMessageList(String username) {
        Long id = userService.getUserIdFromEmail(username);
        List<Long> staffIdList = messageService.getStaffListById(id);
        List<StaffDTO> staffDTO = doctorService.getDoctorsById(staffIdList);
        staffDTO.addAll(staffService.GetStaffById(staffIdList));
        return staffDTO;
    }

    /**
     * Returnerar en lista av alla staff/läkare som har en meddelandekonversation kopplad till en patient.
     * OBS: läkare/staff får ej anropa denna.
     *
     * @param id id, not userId, not fhirId
     * @return
     */
    @GetMapping({"/getPeopleMessageListById", "/getPeopleMessageListById/"})
    public List<StaffDTO> getMessageListById(Long id) {
        List<Long> staffIdList = messageService.getStaffListById(id);
        List<StaffDTO> staffDTO = doctorService.getDoctorsById(staffIdList);
        staffDTO.addAll(staffService.GetStaffById(staffIdList));
        return staffDTO;
    }

    /**
     * returnerar en lista med alla meddelanden mellan två användare
     *
     * @param dto
     * @return
     */
    @GetMapping({"/get", "/get/"})
    public List<Message> getMessage(GetMessageDTO dto) {
        return messageService.getMessageById(dto);
    }

}

