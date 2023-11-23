package algot.emil.messagesapi.utilities;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// Klass för mappstrukturen
public class Helper {

	public static LocalDate DateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}


}
