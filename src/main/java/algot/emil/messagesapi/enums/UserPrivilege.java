package algot.emil.journalapi.enums;

public enum UserPrivilege {
	PATIENT, STAFF, DOCTOR, NONE, NEWPATIENT, NEWSTAFF, NEWDOCTOR
	//NEWPATIENT means that the user is registered as a patient, but a patient hasn't been created connected to that user
}
