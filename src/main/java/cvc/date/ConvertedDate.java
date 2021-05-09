package cvc.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertedDate {
	
	private final String date;
	
	public ConvertedDate(String date) {
		this.date = date;
	}
	
	static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public LocalDate stringToDate() {
		LocalDate localdate = LocalDate.parse(this.date, FORMATTER);
		return localdate;
	}
}
