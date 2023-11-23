package algot.emil.messagesapi.services;


import algot.emil.messagesapi.dto.*;
import algot.emil.messagesapi.enums.Sex;
import algot.emil.messagesapi.utilities.Helper;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FhirService {

	private static final String SERVER_BASE = "https://hapi.fhir.org/baseR4";
	private final IGenericClient client;
	FhirContext context;

	public FhirService() {
		context = FhirContext.forR4();
		client = context.newRestfulGenericClient(SERVER_BASE);
	}

	public PatientFhirDTO getPatientByFhirId(String fhirId) {
		Patient patient = client.read().resource(Patient.class).withId(fhirId).execute();
		PatientFhirDTO dto = new PatientFhirDTO();
		if (patient.hasName()) {
			HumanName humanName = patient.getName().get(0);
			String lastName = humanName.getFamily();
			List<StringType> given = humanName.getGiven();
			StringBuilder firstName = new StringBuilder();
			for (StringType name : given) {
				firstName.append(name.toString()).append(" ");
			}
			firstName.deleteCharAt(firstName.length() - 1);
			dto.setFirstName(firstName.toString());
			dto.setFamilyName(lastName);
		}
		if (patient.hasGender()) {
			String sex = patient.getGender().toString();
			dto.setSex(Sex.valueOf(sex));
		}
		if (patient.hasBirthDate()) {
			Date date = patient.getBirthDate();
			LocalDate birthday = Helper.DateToLocalDate(date);
			dto.setBirthdate(birthday);
		}
		return dto;
	}

}
