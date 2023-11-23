package algot.emil.messagesapi.dto;

import algot.emil.messagesapi.enums.UserPrivilege;

public record StaffDTO(String firstName, String lastName, Long id, UserPrivilege privilege) {
}
