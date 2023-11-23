package algot.emil.messagesapi.services;

import algot.emil.messagesapi.dto.GetMessageDTO;
import algot.emil.messagesapi.dto.SendMessageDTO;
import algot.emil.entities.Message;
import algot.emil.messagesapi.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository repository = null;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void sendMessage(SendMessageDTO dto) {
        if (dto.getEmployeeId() == null || dto.getPatientId() == null) return;
        if (dto.getEmployeeId() < 0 || dto.getPatientId() < 0) return;
        if (dto.getMessage() == null) return;
        if (dto.getMessage().isEmpty()) return;
        Message newMessage = new Message(dto.getEmployeeId(), dto.getPatientId(), dto.getSenderId(), dto.getMessage());
        repository.save(newMessage);
    }

    public List<Message> getMessageById(GetMessageDTO dto) {
        if (dto.getEmployeeId() == null || dto.getPatientId() == null) return null;
        if (dto.getEmployeeId() < 0 || dto.getPatientId() < 0) return null; //TODO: kasta exception
        List<Message> messages = new ArrayList<>();
        messages = repository.findAllByPatientIdAndEmployeeIdOrderByTime(dto.getPatientId(), dto.getEmployeeId());
        List<Message> messages2 = new ArrayList<>();
        messages2 = repository.findAllByEmployeeIdAndPatientIdOrderByTime(dto.getPatientId(), dto.getEmployeeId());
        messages.addAll(messages2);
        //TODO: gör kopia av messages.
        return messages;
    }

    /**
     * returns distinct id:s from doctors and staff which have messages related to a distinct user.
     *
     * @param id id of the user you want to see the staffidlist from.
     * @return
     */
    public List<Long> getStaffListById(Long id) {
        if (id < 0) return null;
        List<Long> staffIdList = repository.findDistinctEmployeeIdsByPatientId(id);
        return staffIdList;
    }

    public List<Long> getPatientListById(Long id) {
        if (id < 0) return null;
        List<Long> patientIdList = repository.findDistinctPatientIdsByEmployeeId(id);
        return patientIdList;
    }
}
