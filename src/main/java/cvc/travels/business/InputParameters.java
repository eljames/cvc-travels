package cvc.travels.business;

import java.time.DateTimeException;
import java.time.LocalDate;

import cvc.date.ConvertedDate;
import lombok.Setter;

@Setter
public class InputParameters {
	
	
	private String checkInDate;
	private String checkOutDate;
	private int numberOfAdults;
	private int numberOfChildren;
	
	public InputParameters(String checkInDate, String checkOutDate, int numberOfAdults, int numberOfChildren) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.setNumberOfAdults(numberOfAdults);
		this.setNumberOfChildren(numberOfChildren);
	}
	
	public InputParameters() {

	}
	
	public LocalDate checkinInDate() throws TreatableException {
		try {
			return new ConvertedDate(this.checkInDate).stringToDate();
		} catch(DateTimeException e) {
			throw new TreatableException(ErrorCodes.CHECKIN_DATE, "Invalid checkin date");
		}
	}
	
	public LocalDate checkinOutDate() throws TreatableException {
		try {
			return new ConvertedDate(this.checkOutDate).stringToDate();
		} catch(DateTimeException e) {
			throw new TreatableException(ErrorCodes.CHECKOUT_DATE, "Invalid checkout date");
		}
	}

	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
}
